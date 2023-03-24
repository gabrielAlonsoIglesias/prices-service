package com.inditex.prices.infrastructure.h2.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.h2.mapper.H2PriceMapper;
import com.inditex.prices.infrastructure.h2.repository.PriceH2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceH2Repository priceH2Repository;

    private final H2PriceMapper h2PriceMapper;

    @Override
    public Optional<Price> findBySearchCriteria(final RequestFindPrice requestFindPrice) {
        return priceH2Repository
            .findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
                requestFindPrice.getProductId(),
                requestFindPrice.getBrandId(),
                requestFindPrice.getApplyDate(),
                requestFindPrice.getApplyDate())
            .map(h2PriceMapper::map);
    }

}
