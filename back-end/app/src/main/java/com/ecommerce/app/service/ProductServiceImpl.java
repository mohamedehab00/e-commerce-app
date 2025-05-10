package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductCategoryWithoutProductsDto;
import com.ecommerce.app.dto.ProductDto;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.repository.ProductCategoryRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final MappingUtilService mappingUtilService;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product product = optionalProduct.get();

        return mappingUtilService.mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll(Integer page, Integer size) {
        Page<Product> products = productRepository.findAll(PageUtil.getPageable(page, size));

        List<Product> productList = products.getContent();

        return convertProductToProductDto(productList);
    }

    @Override
    public List<ProductCategoryWithoutProductsDto> getCategories() {
        return productCategoryRepository.findAll().stream().map(mappingUtilService::mapProductToProductCategoryWithoutProductsDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long id,Integer page, Integer size) {
        Page<Product> products = productRepository.findAllByCategory_Id(id, PageUtil.getPageable(page,size));

        List<Product> productList = products.getContent();

        return convertProductToProductDto(productList);
    }

    @Override
    public List<ProductDto> getProductsByKeyword(String keyword, Integer page, Integer size) {
        Page<Product> products = productRepository.findAllByNameLikeOrDescriptionLike(
                "%"+keyword+"%",
                "%"+keyword+"%", PageUtil.getPageable(page,size));

        List<Product> productList = products.getContent();

        return convertProductToProductDto(productList);
    }

    List<ProductDto> convertProductToProductDto(List<Product> products) {
        return products.stream().map(mappingUtilService::mapProductToProductDto).collect(Collectors.toList());
    }
}
