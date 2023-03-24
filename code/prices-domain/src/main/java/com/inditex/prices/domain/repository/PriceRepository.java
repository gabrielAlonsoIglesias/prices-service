package com.inditex.prices.domain.repository;

import java.util.Optional;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;

public interface PriceRepository {

    Optional<Price> findBySearchCriteria(RequestFindPrice requestFindPrice);

}
