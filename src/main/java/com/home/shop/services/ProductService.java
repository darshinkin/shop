package com.home.shop.services;

import com.home.shop.persistence.dao.ProductDao;
import com.home.shop.routers.models.ProductCreateRequest;
import com.home.shop.routers.models.ProductResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public Mono<ProductResponse> retrieveProductByArticle(long productArticle) {
        return productDao.retrieveProductByIDynamoDB(productArticle).map(product -> ProductResponse.builder()
                .productArticle(product.getArticle())
                .productName(product.getProductName())
                .build());
    }

    public Mono<ProductResponse> save(ProductCreateRequest productRequest) {
        productDao.saveDynamoDb(productRequest);
        return Mono.just(ProductResponse.builder().productArticle(productRequest.getProductArticle()).productName(productRequest.getProductName()).build());
    }
}
