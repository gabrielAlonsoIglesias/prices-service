package com.inditex.prices.domain.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestFindPrice {

    @NotNull
    private LocalDateTime applyDate;

    @NotBlank
    private Long productId;

    @NotBlank
    private Long brandId;

}
