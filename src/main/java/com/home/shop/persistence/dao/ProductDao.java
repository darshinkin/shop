package com.home.shop.persistence.dao;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.home.shop.persistence.models.Product;
import com.home.shop.persistence.repositories.ProductRepository;
import com.home.shop.routers.models.ProductCreateRequest;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductDao {

    private final ProductRepository productRepository;
    private final DynamoDBMapper dynamoDBMapper;

    public Mono<Product> retrieveProductById(long productId) {
        return productRepository.findById(productId);
    }

    public Mono<com.home.shop.persistence.models.dynamodb.Product> retrieveProductByIDynamoDB(long productId) {
        DynamoDBQueryExpression<com.home.shop.persistence.models.dynamodb.Product> dynamoDBQueryExpression =
                new DynamoDBQueryExpression<com.home.shop.persistence.models.dynamodb.Product>()
                        .withHashKeyValues(com.home.shop.persistence.models.dynamodb.Product.builder().id(productId).build());
        PaginatedQueryList<com.home.shop.persistence.models.dynamodb.Product> products =
                dynamoDBMapper.query(com.home.shop.persistence.models.dynamodb.Product.class, dynamoDBQueryExpression);
        return Mono.just(Objects.requireNonNull(products.stream().findFirst().orElse(null)));
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public void saveDynamoDb(ProductCreateRequest productRequest) {
        dynamoDBMapper.save(com.home.shop.persistence.models.dynamodb.Product.builder()
                        .productName(productRequest.getProductName())
                .build());
    }
}
