package com.inditex.prices.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.prices.Application;
import com.inditex.prices.api.rest.dto.PriceDto;
import com.inditex.prices.infrastructure.h2.entity.PriceEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackageClasses = { Application.class })
@EntityScan(basePackageClasses = { PriceEntity.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PricesSteps {

    private static final String BASE_PATH = "/v1/prices";

    private PriceDto priceDto;

    private LocalDateTime applyDateRequest;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Given("^a range of dates with some product prices and priority$")
    public void a_range_of_dates_with_some_product_prices_and_priority() {
        priceDto = null;
    }

    @When("user asks for the price of product id {int} of brand id {int} at {string}")
    public void user_asks_for_the_price_of_product_id_of_brand_id_at(final long productId, final long brandId, final String applyDate) {
        final var builder = UriComponentsBuilder.fromHttpUrl(getServiceUrl() + BASE_PATH)
            .queryParam("apply_date", LocalDateTime.parse(applyDate))
            .queryParam("product_id", productId)
            .queryParam("brand_id", brandId);

        applyDateRequest = LocalDateTime.parse(applyDate);
        priceDto = testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                PriceDto.class)
            .getBody();
    }

    @Then("product id {int}, brand id {int}, price list {int}, price {double} and range dates are answered")
    public void product_id_brand_id_price_list_price_and_range_dates_are_answered(
        final long productId, final long brandId, final long priceList, final double price) {
        
        assertNotNull(priceDto);

        final var expectedPrice = BigDecimal.valueOf(price);
        final var startDate = priceDto.getStartDate();
        final var endDate = priceDto.getEndDate();

        assertEquals(productId, priceDto.getProductId());
        assertEquals(brandId, priceDto.getBrandId());
        assertEquals(priceList, priceDto.getPriceList());
        assertEquals(expectedPrice.compareTo(priceDto.getPrice()), 0);
        assertFalse(applyDateRequest.isBefore(startDate));
        assertFalse(applyDateRequest.isAfter(endDate));
    }

    private HttpHeaders getHttpHeaders() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getServiceUrl() {
        return String.format("http://localhost:%d", this.port);
    }

}
