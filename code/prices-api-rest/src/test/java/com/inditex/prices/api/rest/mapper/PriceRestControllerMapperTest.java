package com.inditex.prices.api.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for mapper")
class PriceRestControllerMapperTest {

    private final PriceRestMapper priceRestMapper = Mappers.getMapper(PriceRestMapper.class);

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    @DisplayName("given a date, product Id, brand Id when domain price mapping is requested then map is done successfully")
    void mapToDomain() {
        final var applyDate = LocalDateTime.now();
        final var productId = 1L;
        final var brandId = 2L;
        final var expectedDomain = RequestFindPrice.builder()
            .applyDate(applyDate)
            .brandId(brandId)
            .productId(productId).build();

        // When
        final var currentDomain = priceRestMapper.map(applyDate, productId, brandId);

        // Then
        assertEquals(expectedDomain, currentDomain);
    }

    @Test
    @DisplayName("given a domain when dto price mapping is requested then map is done successfully")
    void mapToDto() {
        final var domain = easyRandom.nextObject(Price.class);
        final var expectedDto = PriceDto.builder()
            .productId(domain.getProductId())
            .brandId(domain.getBrandId())
            .priceList(domain.getPriceList())
            .startDate(domain.getStartDate())
            .endDate(domain.getEndDate())
            .price(domain.getPrice()).build();

        // When
        final var currentDto = priceRestMapper.map(domain);

        // Then
        assertEquals(expectedDto, currentDto);
    }

}
