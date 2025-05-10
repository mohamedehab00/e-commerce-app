package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderDto {
    private Long id;
    private String trackingNo;

    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private String status;


    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    AddressDto shippingAddress;

    AddressDto billingAddress;

    private List<OrderItemDto> items;
}
