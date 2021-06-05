package com.home.shop.services;

import com.home.shop.persistence.models.Product;
import com.home.shop.persistence.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductService2 {

    private final ProductDao productDao;

    public Mono<Product> retrieveProductById(long productId) {
        return productDao.retrieveProductById(productId);
    }
}
