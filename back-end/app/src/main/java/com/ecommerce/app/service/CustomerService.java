package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDto;

import java.util.List;

public interface CustomerService {
    List<OrderDto> getCustomerOrdersHistory(String customerEmail);
}
