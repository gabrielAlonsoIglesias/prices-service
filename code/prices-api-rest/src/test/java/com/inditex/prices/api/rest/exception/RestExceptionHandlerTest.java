package com.inditex.prices.api.rest.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;

import com.fasterxml.jackson.core.JacksonException;
import com.inditex.prices.api.rest.dto.ErrorDto;
import com.inditex.prices.domain.exception.PriceNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for exceptions handler")
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @ParameterizedTest
    @ValueSource(classes = { PriceNotFoundException.class })
    @DisplayName("given a business exception when is thrown then is mapped to UNPROCESSABLE_ENTITY")
    void handleBusinessExceptions(final Class<Exception> e) {
        final var errorMsg = "error";
        final var exception = mock(e);

        when(exception.getMessage()).thenReturn(errorMsg);

        final var expectedResponse = ResponseEntity.unprocessableEntity()
            .body(ErrorDto.builder()
                .message(exception.getMessage())
                .build());

        final var currentResponse = restExceptionHandler.handleBusinessException(exception);

        verify(exception, atLeast(1)).getMessage();

        assertEquals(expectedResponse, currentResponse);
    }

    @ParameterizedTest
    @ValueSource(classes = {
        JacksonException.class,
        NullPointerException.class,
        BindException.class,
        ServletRequestBindingException.class,
        HttpMessageConversionException.class,
        NumberFormatException.class })
    @DisplayName("given a invalid request exception when is thrown then is mapped to BAD_REQUEST")
    void handleInvalidRequestExceptions(final Class<Exception> e) {
        final var errorMsg = "error";
        final var exception = mock(e);

        when(exception.getMessage()).thenReturn(errorMsg);

        final var expectedResponse = ResponseEntity.badRequest()
            .body(ErrorDto.builder()
                .message(exception.getMessage())
                .build());

        final var currentResponse = restExceptionHandler.handleInvalidRequestException(exception);

        verify(exception, atLeast(1)).getMessage();

        assertEquals(expectedResponse, currentResponse);
    }

}
