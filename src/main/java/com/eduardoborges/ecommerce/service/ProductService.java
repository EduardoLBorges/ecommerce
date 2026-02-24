package com.eduardoborges.ecommerce.service;

import com.eduardoborges.ecommerce.dto.ProductDTO;
import com.eduardoborges.ecommerce.entity.Product;
import com.eduardoborges.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(ProductDTO productDTO){
        var entity = new Product(
                null,
                productDTO.name(),
                productDTO.description(),
                productDTO.price(),
                productDTO.stokQuantity()
                );

        var productSaved = productRepository.save(entity);

        return productSaved.getId();
    }

    public Optional<ProductDTO> getProductById(Long productId){
        return productRepository.findById(productId).
                map(ProductDTO::new);
    }

    public List<ProductDTO> getProductList(){
        return productRepository.findAll().
                stream().
                map(ProductDTO::new).
                toList();
    }

    public void updateProduct(Long productId,
                              ProductDTO productDTO){

        var productEntity = productRepository.findById(productId);

        if (productEntity.isPresent()){
            var product = productEntity.get();

            if (productDTO.name() != null){
                product.setName(productDTO.name());
            }

            if (productDTO.description() != null){
                product.setDescription(productDTO.description());
            }

            if (productDTO.price() != null){
                product.setPrice(productDTO.price());
            }

            if (productDTO.stokQuantity() != null){
                product.setStockQuantity(productDTO.stokQuantity());
            }

            productRepository.save(product);
        }
    }

    public void deleteProductById(Long productId){
        productRepository.deleteById(productId);
    }
}
