package com.home.shop.services;

import com.home.shop.persistence.dao.ProductDao;
import com.home.shop.persistence.models.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public Mono<Product> retrieveProductById(long productId) {
        return productDao.retrieveProductById(productId);
    }

    public Mono<Product> save(Product product) {
        return productDao.save(product);
    }
}
