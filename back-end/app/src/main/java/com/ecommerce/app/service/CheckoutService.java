package com.ecommerce.app.service;

import com.ecommerce.app.dto.PurchaseRequest;
import com.ecommerce.app.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(PurchaseRequest request);
}
