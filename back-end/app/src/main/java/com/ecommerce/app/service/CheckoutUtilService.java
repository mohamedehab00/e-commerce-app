package com.ecommerce.app.service;

import com.ecommerce.app.dto.AddressDetails;
import com.ecommerce.app.dto.CartItem;
import com.ecommerce.app.dto.CustomerDetails;
import com.ecommerce.app.dto.PurchaseRequest;
import com.ecommerce.app.model.*;
import com.ecommerce.app.repository.CityRepository;
import com.ecommerce.app.repository.CountryRepository;
import com.ecommerce.app.repository.CustomerRepository;
import com.ecommerce.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class CheckoutUtilService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public Customer getCustomer(PurchaseRequest request) {
        if (request.getCustomerDetails() == null) {
            throw new RuntimeException("Customer details is null");
        }

        CustomerDetails customerDetails = request.getCustomerDetails();

        if (customerDetails.getFirstName() == null ||
                customerDetails.getLastName() == null ||
                customerDetails.getEmail() == null ||
                customerDetails.getPhone() == null) {
            throw new RuntimeException("Customer details is invalid");
        }

        Optional<Customer> customerOptional = customerRepository.findByEmail(customerDetails.getEmail());

        if (customerOptional.isPresent()) {
            return customerOptional.get();
        }

        Customer customer = new Customer();
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        return customer;
    }

    public Address getShippingAddress(PurchaseRequest request) {
        if (request.getShippingDetails() == null) {
            throw new RuntimeException("Shipping address is null");
        }

        AddressDetails shippingAddressDetails = request.getShippingDetails();

        if (validateAddress(shippingAddressDetails)){
            throw new RuntimeException("Shipping address is invalid");
        }

        Optional<Country> country = countryRepository.findById(shippingAddressDetails.getCountry());
        Optional<City> city = cityRepository.findById(shippingAddressDetails.getCity());

        if (country.isEmpty() || city.isEmpty()) {
            throw new RuntimeException("Shipping address is invalid");
        }

        Address shippingAddress = new Address();
        shippingAddress.setStreet(shippingAddressDetails.getStreet());
        shippingAddress.setCity(city.get());
        shippingAddress.setCountry(country.get());
        shippingAddress.setZipCode(shippingAddressDetails.getZip());

        return shippingAddress;
    }

    public Address getBillingAddress(PurchaseRequest request) {
        if (request.getBillingDetails() == null) {
            throw new RuntimeException("Billing address is null");
        }

        AddressDetails billingAddressDetails = request.getBillingDetails();

        if (validateAddress(billingAddressDetails)){
            throw new RuntimeException("Billing address is invalid");
        }

        Optional<Country> country = countryRepository.findById(billingAddressDetails.getCountry());
        Optional<City> city = cityRepository.findById(billingAddressDetails.getCity());

        if (country.isEmpty() || city.isEmpty()) {
            throw new RuntimeException("Billing address is invalid");
        }

        Address billingAddress = new Address();
        billingAddress.setStreet(billingAddressDetails.getStreet());
        billingAddress.setCity(city.get());
        billingAddress.setCountry(country.get());
        billingAddress.setZipCode(billingAddressDetails.getZip());

        return billingAddress;
    }

    boolean validateAddress(AddressDetails address) {
        return address.getStreet() == null ||
                address.getCity() == null ||
                address.getCountry() == null ||
                address.getZip() == null;
    }

    public Pair<List<OrderItem>,Pair<Integer, BigDecimal>> prepareOrderItems(List<CartItem> cartItems, Order order) {
        AtomicReference<Integer> quantity = new AtomicReference<>(0);
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);

        List<OrderItem> orderItems = new ArrayList<>();

        cartItems.forEach(cartItem -> {
            Optional<Product> optionalProduct = productRepository.findById(cartItem.getId());

            if (optionalProduct.isEmpty()){
                throw new RuntimeException("Product not found");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setImgUrl(cartItem.getImageUrl());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getPrice());
            orderItem.setProduct(optionalProduct.get());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            quantity.updateAndGet(v -> v + cartItem.getQuantity());
            final Double price = cartItem.getPrice().doubleValue() * cartItem.getQuantity();
            totalPrice.updateAndGet(v -> v + price);
        });

        return Pair.of(orderItems,Pair.of(quantity.get(), BigDecimal.valueOf(totalPrice.get())));
    }

    public Order prepareOrder(PurchaseRequest request) {
        Customer customer = getCustomer(request);

        Address shippingAddress = getShippingAddress(request);

        Address billingAddress = null;

        if (!request.getSameAsShipping()){
            billingAddress = getBillingAddress(request);
        }

        Order order = new Order();
        order.setStatus("PENDING");
        order.setTrackingNo(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);

        Pair<List<OrderItem>, Pair<Integer, BigDecimal>> orderDetails = prepareOrderItems(request.getCart(), order);

        order.setItems(orderDetails.getFirst());
        order.setTotalPrice(orderDetails.getSecond().getSecond());
        order.setTotalQuantity(orderDetails.getSecond().getFirst());

        return order;
    }
}
