package com.inditex.prices.application;

import org.springframework.stereotype.Component;

import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.domain.usecase.PriceUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceUseCaseImpl implements PriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price findPrice(final RequestFindPrice requestFindPrice) {
        return priceRepository.findBySearchCriteria(requestFindPrice)
            .orElseThrow(() -> new PriceNotFoundException(String.format("Price not found for request: %s", requestFindPrice)));
    }

}
