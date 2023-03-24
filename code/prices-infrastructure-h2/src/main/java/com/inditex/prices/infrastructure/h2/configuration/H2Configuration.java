package com.inditex.prices.infrastructure.h2.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.inditex.prices.infrastructure.h2.entity.PriceEntity;
import com.inditex.prices.infrastructure.h2.repository.PriceH2Repository;

@Configuration
@EnableJpaRepositories(basePackageClasses = { PriceH2Repository.class })
@EntityScan(basePackageClasses = PriceEntity.class)
public class H2Configuration {

}
