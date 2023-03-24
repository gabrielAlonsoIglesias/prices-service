package com.inditex.prices.infrastructure.h2.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.prices.infrastructure.h2.entity.PriceEntity;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findFirstByProductIdAndBrandIdAndStartDateIsLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
        final Long productId,
        final Long brandId,
        final LocalDateTime startDate,
        final LocalDateTime endDate);

}
