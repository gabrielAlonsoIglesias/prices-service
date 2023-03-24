package com.inditex.prices.api.rest.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PriceDto
 */
@lombok.Builder @lombok.NoArgsConstructor @lombok.AllArgsConstructor

@JsonTypeName("Price")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-24T15:57:29.784284500+01:00[Europe/Paris]")
public class PriceDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("brand_id")
  private Long brandId;

  @JsonProperty("price_list")
  private Long priceList;

  @JsonProperty("start_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private java.time.LocalDateTime startDate;

  @JsonProperty("end_date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private java.time.LocalDateTime endDate;

  @JsonProperty("price")
  private java.math.BigDecimal price;

  public PriceDto productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Product id
   * @return productId
  */
  
  @Schema(name = "product_id", example = "35455", description = "Product id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public PriceDto brandId(Long brandId) {
    this.brandId = brandId;
    return this;
  }

  /**
   * Brand id
   * @return brandId
  */
  
  @Schema(name = "brand_id", example = "1", description = "Brand id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public PriceDto priceList(Long priceList) {
    this.priceList = priceList;
    return this;
  }

  /**
   * Applied rate id
   * @return priceList
  */
  
  @Schema(name = "price_list", example = "3", description = "Applied rate id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getPriceList() {
    return priceList;
  }

  public void setPriceList(Long priceList) {
    this.priceList = priceList;
  }

  public PriceDto startDate(java.time.LocalDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Start date for applied price
   * @return startDate
  */
  @Valid 
  @Schema(name = "start_date", description = "Start date for applied price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public java.time.LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(java.time.LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public PriceDto endDate(java.time.LocalDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * End date for applied price
   * @return endDate
  */
  @Valid 
  @Schema(name = "end_date", description = "End date for applied price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public java.time.LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(java.time.LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public PriceDto price(java.math.BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Applied price
   * @return price
  */
  
  @Schema(name = "price", example = "35.5", description = "Applied price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public java.math.BigDecimal getPrice() {
    return price;
  }

  public void setPrice(java.math.BigDecimal price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceDto price = (PriceDto) o;
    return Objects.equals(this.productId, price.productId) &&
        Objects.equals(this.brandId, price.brandId) &&
        Objects.equals(this.priceList, price.priceList) &&
        Objects.equals(this.startDate, price.startDate) &&
        Objects.equals(this.endDate, price.endDate) &&
        Objects.equals(this.price, price.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, brandId, priceList, startDate, endDate, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceDto {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    brandId: ").append(toIndentedString(brandId)).append("\n");
    sb.append("    priceList: ").append(toIndentedString(priceList)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

