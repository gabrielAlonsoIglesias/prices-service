package com.inditex.prices.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import com.inditex.prices.domain.repository.PriceRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for uses cases")
class PriceUseCaseImplTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private PriceUseCaseImpl priceUseCaseImpl;

    @Mock
    private PriceRepository priceRepository;

    @Test
    @DisplayName("given a request of price finding when price retrieval is requested then it is found successfully")
    void findPrice_whenFound() {
        final var requestFindPrice = easyRandom.nextObject(RequestFindPrice.class);
        final var price = easyRandom.nextObject(Price.class);

        // Given
        when(priceRepository.findBySearchCriteria(requestFindPrice)).thenReturn(Optional.of(price));

        // When
        final var foundPrice = priceUseCaseImpl.findPrice(requestFindPrice);

        // Then
        verify(priceRepository).findBySearchCriteria(requestFindPrice);

        assertEquals(price, foundPrice);
    }

    @Test
    @DisplayName("given a request of price finding when price retrieval is requested then business exception is thrown")
    void findPrice_whenNotFound() {
        final var requestFindPrice = easyRandom.nextObject(RequestFindPrice.class);

        // Given
        when(priceRepository.findBySearchCriteria(requestFindPrice)).thenReturn(Optional.empty());

        // When
        assertThrows(PriceNotFoundException.class, () -> priceUseCaseImpl.findPrice(requestFindPrice));

        // Then
        verify(priceRepository).findBySearchCriteria(requestFindPrice);
    }

}
