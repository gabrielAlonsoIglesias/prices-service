package com.inditex.prices.infrastructure.h2.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.h2.entity.PriceEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T15:57:39+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Eclipse Adoptium)"
)
@Component
public class H2PriceMapperImpl extends H2PriceMapper {

    @Override
    public Price map(PriceEntity priceEntity) {
        if ( priceEntity == null ) {
            return null;
        }

        Price.PriceBuilder price = Price.builder();

        if ( priceEntity.getProductId() != null ) {
            price.productId( priceEntity.getProductId() );
        }
        if ( priceEntity.getBrandId() != null ) {
            price.brandId( priceEntity.getBrandId() );
        }
        if ( priceEntity.getPriceList() != null ) {
            price.priceList( priceEntity.getPriceList() );
        }
        if ( priceEntity.getStartDate() != null ) {
            price.startDate( priceEntity.getStartDate() );
        }
        if ( priceEntity.getEndDate() != null ) {
            price.endDate( priceEntity.getEndDate() );
        }
        if ( priceEntity.getPrice() != null ) {
            price.price( priceEntity.getPrice() );
        }
        price.priority( priceEntity.getPriority() );
        if ( priceEntity.getCurrency() != null ) {
            price.currency( priceEntity.getCurrency() );
        }

        return price.build();
    }
}
