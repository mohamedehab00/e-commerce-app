package com.ecommerce.app.service;

import com.ecommerce.app.dto.*;
import com.ecommerce.app.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingUtilService {
    ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .imageUrl(product.getImageUrl())
                .isActive(product.getIsActive())
                .unitsInStock(product.getUnitsInStock())
                .category(this.mapProductToProductCategoryWithoutProductsDto(product.getCategory()))
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    ProductCategoryWithoutProductsDto mapProductToProductCategoryWithoutProductsDto(ProductCategory productCategory) {
        return ProductCategoryWithoutProductsDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .build();
    }

    ProductWithoutCategoryDto mapProductToProductWithoutCategoryDto(Product product) {
        return ProductWithoutCategoryDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .imageUrl(product.getImageUrl())
                .isActive(product.getIsActive())
                .unitsInStock(product.getUnitsInStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    ProductCategoryDto mapProductCategoryToProductCategoryDto(ProductCategory productCategory, List<Product> products) {
        return ProductCategoryDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .productsWithoutCategoryDto(products.stream().map(this::mapProductToProductWithoutCategoryDto).collect(Collectors.toList()))
                .build();
    }

    CountryDto mapCountryToCountryDto(Country country){
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .cities(country.getCities().stream().map(this::mapCityToCityDto).toList())
                .build();
    }

    CityDto mapCityToCityDto(City city){
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    OrderDto mapOrderToOrderDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .trackingNo(order.getTrackingNo())
                .dateCreated(order.getDateCreated())
                .lastUpdated(order.getLastUpdated())
                .items(order.getItems().stream().map(this::mapOrderItemToOrderItemDto).toList())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .shippingAddress(this.mapAddressToAddressDto(order.getShippingAddress()))
                .billingAddress(order.getBillingAddress()!=null ? this.mapAddressToAddressDto(order.getBillingAddress()):null)
                .build();
    }

    AddressDto mapAddressToAddressDto(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity().getName())
                .country(address.getCountry().getName())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();
    }

    OrderItemDto mapOrderItemToOrderItemDto(OrderItem orderItem){
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .imgUrl(orderItem.getImgUrl())
                .product(orderItem.getProduct())
                .build();
    }
}
