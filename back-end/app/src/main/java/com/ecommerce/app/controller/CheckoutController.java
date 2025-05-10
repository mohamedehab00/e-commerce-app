package com.ecommerce.app.controller;

import com.ecommerce.app.dto.PurchaseRequest;
import com.ecommerce.app.dto.PurchaseResponse;
import com.ecommerce.app.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> checkout(@RequestBody PurchaseRequest purchaseRequest) {
        return new ResponseEntity<>(checkoutService.placeOrder(purchaseRequest), HttpStatus.CREATED);
    }
}
