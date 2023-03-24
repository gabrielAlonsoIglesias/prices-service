package com.inditex.prices.api.rest.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.prices.api.rest.api.PricesApi;
import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.api.rest.mapper.PriceRestMapper;
import com.inditex.prices.domain.usecase.PriceUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class PriceRestController implements PricesApi {

    private final PriceUseCase priceUseCase;

    private final PriceRestMapper priceRestMapper;

    @Override
    public ResponseEntity<PriceDto> getPriceByDateAndProductAndBrand(final LocalDateTime applyDate, final Long productId,
                                                                     final Long brandId) {

        log.debug("Action: getPriceByDateAndProductAndBrand; Params: applyDate={}, productId={}, brandId={}; Message: Enter;",
            applyDate, productId, brandId);

        final var requestFindPrice = priceRestMapper.map(applyDate, productId, brandId);
        final var price = priceUseCase.findPrice(requestFindPrice);

        log.debug("Action: getPriceByDateAndProductAndBrand; Message: Exit. Price={};", price);

        return ResponseEntity.ok(priceRestMapper.map(price));
    }

}
