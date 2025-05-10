package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PurchaseRequest {
    private CustomerDetails customerDetails;
    private AddressDetails shippingDetails;
    private AddressDetails billingDetails;
    private List<CartItem> cart;
    private Boolean sameAsShipping;
    private Long paymentType;
}
