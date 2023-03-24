package com.inditex.prices.domain.usecase;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;

public interface PriceUseCase {

    Price findPrice(RequestFindPrice requestFindPrice);

}
