package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductCategoryDto;

public interface ProductCategoriesService {
    ProductCategoryDto getProductCategory(Long id, Integer page, Integer size);
}
