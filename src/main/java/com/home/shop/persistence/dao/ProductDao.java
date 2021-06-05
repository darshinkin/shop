package com.home.shop.persistence.dao;

import com.home.shop.persistence.models.Product;
import com.home.shop.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductDao {

    private final ProductRepository productRepository;

    public Mono<Product> retrieveProductById(long productId) {
        return productRepository.findById(productId);
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }
}
