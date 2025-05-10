package com.ecommerce.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductCategoryDto {
    private Long id;
    private String name;

    @JsonProperty(value = "products")
    private List<ProductWithoutCategoryDto> productsWithoutCategoryDto;
}
