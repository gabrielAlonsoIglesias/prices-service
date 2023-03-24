package com.inditex.prices.api.rest.mapper;

import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T15:57:32+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Eclipse Adoptium)"
)
@Component
public class PriceRestMapperImpl extends PriceRestMapper {

    @Override
    public RequestFindPrice map(LocalDateTime applyDate, Long productId, Long brandId) {
        if ( applyDate == null && productId == null && brandId == null ) {
            return null;
        }

        RequestFindPrice.RequestFindPriceBuilder requestFindPrice = RequestFindPrice.builder();

        if ( applyDate != null ) {
            requestFindPrice.applyDate( applyDate );
        }
        if ( productId != null ) {
            requestFindPrice.productId( productId );
        }
        if ( brandId != null ) {
            requestFindPrice.brandId( brandId );
        }

        return requestFindPrice.build();
    }

    @Override
    public PriceDto map(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDto.PriceDtoBuilder priceDto = PriceDto.builder();

        if ( price.getProductId() != null ) {
            priceDto.productId( price.getProductId() );
        }
        if ( price.getBrandId() != null ) {
            priceDto.brandId( price.getBrandId() );
        }
        if ( price.getPriceList() != null ) {
            priceDto.priceList( price.getPriceList() );
        }
        if ( price.getStartDate() != null ) {
            priceDto.startDate( price.getStartDate() );
        }
        if ( price.getEndDate() != null ) {
            priceDto.endDate( price.getEndDate() );
        }
        if ( price.getPrice() != null ) {
            priceDto.price( price.getPrice() );
        }

        return priceDto.build();
    }
}
