package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PurchaseResponse {
    private String trackingNo;
}
