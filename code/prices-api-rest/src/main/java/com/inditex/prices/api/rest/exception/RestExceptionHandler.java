package com.inditex.prices.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JacksonException;
import com.inditex.prices.api.rest.dto.ErrorDto;
import com.inditex.prices.domain.exception.PriceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = { PriceNotFoundException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorDto> handleBusinessException(final Exception e) {
        writeLog(e);
        return ResponseEntity.unprocessableEntity().body(ErrorDto.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {
        JacksonException.class,
        NullPointerException.class,
        BindException.class,
        ServletRequestBindingException.class,
        HttpMessageConversionException.class,
        NumberFormatException.class,
        IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleInvalidRequestException(final Exception e) {
        writeLog(e);
        return ResponseEntity.badRequest().body(ErrorDto.builder().message(e.getMessage()).build());
    }

    private void writeLog(final Exception e) {
        log.error("Action: writeLog; Exception message: {}", e.getMessage(), e);
    }

}
