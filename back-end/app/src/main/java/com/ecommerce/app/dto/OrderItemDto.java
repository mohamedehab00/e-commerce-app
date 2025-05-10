package com.ecommerce.app.dto;

import com.ecommerce.app.model.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDto {
    private Long id;
    private String imgUrl;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Product product;
}
