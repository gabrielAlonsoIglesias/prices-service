package com.inditex.prices.infrastructure.h2.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.h2.entity.PriceEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for mapper")
class H2PriceMapperTest {

    private final H2PriceMapper h2PriceMapper = Mappers.getMapper(H2PriceMapper.class);

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    @DisplayName("given an entity when domain mapping is requested then map is done successfully")
    void mapToDomain() {
        final var entity = easyRandom.nextObject(PriceEntity.class);
        final var expectedDomain = Price.builder()
            .productId(entity.getProductId())
            .brandId(entity.getBrandId())
            .priceList(entity.getPriceList())
            .startDate(entity.getStartDate())
            .endDate(entity.getEndDate())
            .price(entity.getPrice())
            .priority(entity.getPriority())
            .currency(entity.getCurrency()).build();

        // When
        final var currentDomain = h2PriceMapper.map(entity);

        // Then
        assertEquals(expectedDomain, currentDomain);
    }

}