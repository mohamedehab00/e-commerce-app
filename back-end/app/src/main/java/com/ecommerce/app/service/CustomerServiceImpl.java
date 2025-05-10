package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDto;
import com.ecommerce.app.model.Order;
import com.ecommerce.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final OrderRepository orderRepository;
    private final MappingUtilService mappingUtilService;

    @Override
    public List<OrderDto> getCustomerOrdersHistory(String customerEmail) {
        List<Order> orders = orderRepository.findByCustomer_Email(customerEmail);

        return orders.stream().map(mappingUtilService::mapOrderToOrderDto).collect(Collectors.toList());
    }
}
