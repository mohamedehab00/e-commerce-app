package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductCategoryWithoutProductsDto;
import com.ecommerce.app.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    List<ProductDto> getAll(Integer page, Integer size);
    List<ProductCategoryWithoutProductsDto> getCategories();
    List<ProductDto> getProductsByCategory(Long categoryId, Integer page, Integer size);
    List<ProductDto> getProductsByKeyword(String keyword, Integer page, Integer size);
}
