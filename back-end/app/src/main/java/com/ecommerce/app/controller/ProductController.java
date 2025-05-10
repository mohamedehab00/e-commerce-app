package com.ecommerce.app.controller;

import com.ecommerce.app.dto.ProductCategoryDto;
import com.ecommerce.app.dto.ProductCategoryWithoutProductsDto;
import com.ecommerce.app.dto.ProductDto;
import com.ecommerce.app.service.ProductCategoriesService;
import com.ecommerce.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductCategoriesService productCategoriesService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size
    ) {
        return new ResponseEntity<>(productService.getAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryWithoutProductsDto>> getAllCategories(){
        return new ResponseEntity<>(productService.getCategories() , HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ProductCategoryDto> getProductCategoryById(
            @PathVariable(value = "id") Long id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
            ){
        return new ResponseEntity<>(productCategoriesService.getProductCategory(id, page, size) , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        return new ResponseEntity<>(productService.getProductsByKeyword(keyword, page,size) , HttpStatus.OK);
    }
}
