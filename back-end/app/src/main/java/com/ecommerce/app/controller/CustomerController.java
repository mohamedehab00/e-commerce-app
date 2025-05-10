package com.ecommerce.app.controller;

import com.ecommerce.app.dto.OrderDto;
import com.ecommerce.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getCustomerOrders(@RequestParam String email) {
        return new ResponseEntity<>(customerService.getCustomerOrdersHistory(email), HttpStatus.OK);
    }
}
