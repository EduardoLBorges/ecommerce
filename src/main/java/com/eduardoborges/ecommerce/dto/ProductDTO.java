package com.eduardoborges.ecommerce.dto;

import com.eduardoborges.ecommerce.entity.Product;

import java.math.BigDecimal;

public record ProductDTO(Long id,
                         String name,
                         String description,
                         BigDecimal price,
                         Integer stokQuantity) {
    public ProductDTO(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }
}

