package com.ecommerce.app.service;

import com.ecommerce.app.dto.PurchaseRequest;
import com.ecommerce.app.dto.PurchaseResponse;
import com.ecommerce.app.model.*;
import com.ecommerce.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CheckoutUtilService checkoutUtilService;
    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @Override
    public PurchaseResponse placeOrder(PurchaseRequest request) {
        Order order = checkoutUtilService.prepareOrder(request);

        Order savedOrder = orderRepository.saveAndFlush(order);

        notifyCustomer(savedOrder);

        return PurchaseResponse.builder()
                .trackingNo(savedOrder.getTrackingNo())
                .build();
    }

    private void notifyCustomer(Order savedOrder) {
        emailService.sendEmail(
                savedOrder.getCustomer().getEmail(),
                "Order Confirmation",
                HtmlTemplateUtil.getHtmlTemplate(
                        savedOrder.getCustomer().getFirstName()+" "+savedOrder.getCustomer().getLastName(),
                        savedOrder.getTrackingNo(),
                        savedOrder.getTotalQuantity(),
                        savedOrder.getTotalPrice().doubleValue()
                )
        );
    }
}
