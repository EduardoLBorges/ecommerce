package com.eduardoborges.ecommerce.controller;

import com.eduardoborges.ecommerce.dto.ProductDTO;
import com.eduardoborges.ecommerce.entity.Product;
import com.eduardoborges.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductDTO productDTO){

        var productId = productService.createProduct(productDTO);

        return ResponseEntity.created(URI.create("/products" + productId.toString())).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDTO>> getProductById(@PathVariable Long productId){

        var product = productService.getProductById(productId);

        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public List<ProductDTO> getProductList(){

        return productService.getProductList();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,
                                              @RequestBody ProductDTO productDTO){
        productService.updateProduct(productId, productDTO);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId){

        productService.deleteProductById(productId);
    }
}
