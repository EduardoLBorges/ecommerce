package com.eduardoborges.ecommerce.repository;

import com.eduardoborges.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
