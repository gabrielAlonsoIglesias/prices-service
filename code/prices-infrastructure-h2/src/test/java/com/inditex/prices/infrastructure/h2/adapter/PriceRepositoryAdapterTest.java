package com.inditex.prices.infrastructure.h2.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.model.RequestFindPrice;
import com.inditex.prices.infrastructure.h2.entity.PriceEntity;
import com.inditex.prices.infrastructure.h2.mapper.H2PriceMapper;
import com.inditex.prices.infrastructure.h2.repository.PriceH2Repository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit testing for adapter")
class PriceRepositoryAdapterTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Mock
    private H2PriceMapper h2PriceMapper;

    @Mock
    private PriceH2Repository priceH2Repository;

    @Test
    @DisplayName("given a search criteria when find is requested then a result is retrieved")
    void findBySearchCriteria_whenFound() {
        final var requestFindPrice = mock(RequestFindPrice.class);
        final var productId = 2L;
        final var brandId = 1L;
        final var date = LocalDateTime.now();
        final var entity = easyRandom.nextObject(PriceEntity.class);
        final var price = easyRandom.nextObject(Price.class);

        requestFindPrice.setApplyDate(date);
        requestFindPrice.setProductId(productId);
        requestFindPrice.setBrandId(brandId);

        // Given
        when(requestFindPrice.getApplyDate()).thenReturn(date);
        when(requestFindPrice.getProductId()).thenReturn(productId);
        when(requestFindPrice.getBrandId()).thenReturn(brandId);
        when(priceH2Repository
            .findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, date, date))
            .thenReturn(Optional.of(entity));
        when(h2PriceMapper.map(entity)).thenReturn(price);

        // When
        final var foundPriceOpt = priceRepositoryAdapter.findBySearchCriteria(requestFindPrice);

        // Then
        verify(priceH2Repository)
            .findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, date, date);
        verify(h2PriceMapper).map(entity);

        assertTrue(foundPriceOpt.isPresent());
        assertEquals(price, foundPriceOpt.get());
    }

    @Test
    @DisplayName("given a search criteria when find is requested then no result is retrieved")
    void findBySearchCriteria_whenNotFound() {
        final var requestFindPrice = mock(RequestFindPrice.class);
        final var productId = 2L;
        final var brandId = 1L;
        final var date = LocalDateTime.now();

        // Given
        when(requestFindPrice.getApplyDate()).thenReturn(date);
        when(requestFindPrice.getProductId()).thenReturn(productId);
        when(requestFindPrice.getBrandId()).thenReturn(brandId);
        when(priceH2Repository
            .findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, date, date))
            .thenReturn(Optional.empty());

        // When
        final var foundPriceOpt = priceRepositoryAdapter.findBySearchCriteria(requestFindPrice);

        verify(priceH2Repository)
            .findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, date, date);
        verify(h2PriceMapper, never()).map(any(PriceEntity.class));

        assertTrue(foundPriceOpt.isEmpty());
    }

}
