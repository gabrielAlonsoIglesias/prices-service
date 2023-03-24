package com.inditex.prices.api.rest.mapper;

import java.time.LocalDateTime;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;

@Mapper(
    componentModel = "spring",
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PriceRestMapper {

    public abstract RequestFindPrice map(LocalDateTime applyDate, Long productId, Long brandId);

    public abstract PriceDto map(Price price);
}
