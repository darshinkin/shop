package com.home.shop.services;

import com.home.shop.persistence.dao.ProductDao;
import com.home.shop.routers.models.ProductCreateRequest;
import com.home.shop.routers.models.ProductResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public Mono<ProductResponse> retrieveProductById(long productId) {
        return productDao.retrieveProductByIDynamoDB(productId).map(product -> ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .build());
    }

    public Mono<ProductResponse> save(ProductCreateRequest productRequest) {
        productDao.saveDynamoDb(productRequest);
        return Mono.just(ProductResponse.builder().productName(productRequest.getProductName()).build());
    }
}
