package com.inditex.prices.infrastructure.h2.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.h2.entity.PriceEntity;

@Mapper(
    componentModel = "spring",
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class H2PriceMapper {

    public abstract Price map(PriceEntity priceEntity);

}
