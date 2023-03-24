package com.inditex.prices.infrastructure.h2.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PRICE")
public class PriceEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "PRICE_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PRICE_SEQ", sequenceName = "PRICE_SEQ", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand_id", nullable = false, updatable = false)
    private Long brandId;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDateTime endDate;

    @Column(name = "price_list", nullable = false, updatable = false)
    private Long priceList;

    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId;

    @Column(name = "priority", nullable = false, updatable = false)
    private int priority;

    @Column(name = "price", nullable = false, updatable = false)
    private BigDecimal price;

    @Column(name = "currency", nullable = false, updatable = false)
    private String currency;

}
