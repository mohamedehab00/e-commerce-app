package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ProductWithoutCategoryDto {
    private Long id;

    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private Boolean isActive;

    private Integer unitsInStock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
