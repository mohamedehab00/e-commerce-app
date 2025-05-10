package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductCategoryDto;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.model.ProductCategory;
import com.ecommerce.app.repository.ProductCategoryRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductCategoriesServiceImpl implements ProductCategoriesService {
    private final MappingUtilService mappingUtilService;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductCategoryDto getProductCategory(Long id, Integer page, Integer size) {
        Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        ProductCategory productCategory = optionalCategory.get();

        Page<Product> associatedProductsPage = productRepository.findAllByCategory_Id(productCategory.getId(), PageUtil.getPageable(page, size));

        List<Product> associatedProducts = associatedProductsPage.getContent();

        return mappingUtilService.mapProductCategoryToProductCategoryDto(productCategory, associatedProducts);
    }
}
