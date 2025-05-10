package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CartItem {
    private Long id;
    private String imageUrl;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
