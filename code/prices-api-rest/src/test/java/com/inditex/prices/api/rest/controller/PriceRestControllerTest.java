package com.inditex.prices.api.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.api.rest.mapper.PriceRestMapper;
import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import com.inditex.prices.domain.usecase.PriceUseCase;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for controller")
class PriceRestControllerTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private PriceRestController priceRestController;

    @Mock
    private PriceUseCase priceUseCase;

    @Mock
    private PriceRestMapper priceRestMapper;

    @Test
    @DisplayName("given a date, brand Id, product Id when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenFound() {
        final var date = LocalDateTime.now();
        final var productId = 2L;
        final var brandId = 1L;
        final var price = easyRandom.nextObject(Price.class);
        final var priceDto = easyRandom.nextObject(PriceDto.class);
        final var requestFindPrice = easyRandom.nextObject(RequestFindPrice.class);

        // Given
        when(priceRestMapper.map(date, productId, brandId)).thenReturn(requestFindPrice);
        when(priceUseCase.findPrice(eq(requestFindPrice))).thenReturn(price);
        when(priceRestMapper.map(eq(price))).thenReturn(priceDto);

        // When
        final var foundPrice = priceRestController.getPriceByDateAndProductAndBrand(date, productId, brandId);

        // Then
        verify(priceRestMapper).map(date, productId, brandId);
        verify(priceRestMapper).map(price);
        verify(priceUseCase).findPrice(requestFindPrice);

        assertNotNull(foundPrice);
        assertEquals(HttpStatus.OK, foundPrice.getStatusCode());
        assertEquals(priceDto, foundPrice.getBody());
    }

    @Test
    @DisplayName("given a date, brand Id, product Id when price retrieval is requested then business exception is thrown and managed")
    void getPriceByDateAndProductAndBrand_whenNotFound() {
        final var date = LocalDateTime.now();
        final var productId = 2L;
        final var brandId = 1L;
        final var requestFindPrice = easyRandom.nextObject(RequestFindPrice.class);

        // Given
        when(priceRestMapper.map(date, productId, brandId)).thenReturn(requestFindPrice);
        when(priceUseCase.findPrice(eq(requestFindPrice))).thenThrow(PriceNotFoundException.class);

        // When
        assertThrows(PriceNotFoundException.class,
            () -> priceRestController.getPriceByDateAndProductAndBrand(date, productId, brandId));

        // Then
        verify(priceRestMapper).map(date, productId, brandId);
        verify(priceRestMapper, never()).map(any(Price.class));
        verify(priceUseCase).findPrice(requestFindPrice);
    }

}
