package com.ecommerce.app.repository;

import com.ecommerce.app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory_Id(Long categoryId, Pageable pageable);
    Page<Product> findAllByNameLikeOrDescriptionLike(String keyword, String keyword1, Pageable pageable);
}
