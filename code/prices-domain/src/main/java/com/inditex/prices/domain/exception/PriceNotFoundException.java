package com.inditex.prices.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(final String message) {
        super(message);
    }

}
