package com.inditex.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.prices.api.rest.dto.PriceDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@DisplayName("Integration tests")
class PriceRestControllerIT {

    private static final String BASE_PATH = "/v1/prices";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        final var converter = new MappingJackson2HttpMessageConverter();
        testRestTemplate.getRestTemplate().setMessageConverters(List.of(converter));
    }

    @Test
    @DisplayName("given scenario 1 data when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenScenario1() {
        final var expectedPrice = BigDecimal.valueOf(35.50);
        final var expectedPriceList = 1L;
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-14T10:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 1L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()), 0);
        assertEquals(expectedPriceList, response.getBody().getPriceList());
    }

    @Test
    @DisplayName("given scenario 2 data when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenScenario2() {
        final var expectedPrice = BigDecimal.valueOf(25.45);
        final var expectedPriceList = 2L;
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-14T16:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 1L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()), 0);
        assertEquals(expectedPriceList, response.getBody().getPriceList());
    }

    @Test
    @DisplayName("given scenario 3 data when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenScenario3() {
        final var expectedPrice = BigDecimal.valueOf(35.50);
        final var expectedPriceList = 1L;
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-14T21:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 1L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()), 0);
        assertEquals(expectedPriceList, response.getBody().getPriceList());
    }

    @Test
    @DisplayName("given scenario 4 data when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenScenario4() {
        final var expectedPrice = BigDecimal.valueOf(30.50);
        final var expectedPriceList = 3L;
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-15T10:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 1L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()), 0);
        assertEquals(expectedPriceList, response.getBody().getPriceList());
    }

    @Test
    @DisplayName("given scenario 5 data when price retrieval is requested then it is found successfully")
    void getPriceByDateAndProductAndBrand_whenScenario5() {
        final var expectedPrice = BigDecimal.valueOf(38.95);
        final var expectedPriceList = 4L;
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-16T21:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 1L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()), 0);
        assertEquals(expectedPriceList, response.getBody().getPriceList());
    }

    @Test
    @DisplayName("given a non existing brand Id when price retrieval is requested then a UNPROCESSABLE_ENTITY response is retrieved")
    void getTransactionStatus_UnprocessableEntity() {
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-14T10:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", 2L);

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    @DisplayName("given a empty brand Id when price retrieval is requested then a BAD_REQUEST response is retrieved")
    void getTransactionStatus_BadRequest() {
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", "2020-06-14T10:00:00")
            .queryParam("product_id", 35455L)
            .queryParam("brand_id", "");

        final var response =
            testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private HttpHeaders getHttpHeaders() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getServiceUrl() {
        return String.format("http://localhost:%d", port);
    }

}
