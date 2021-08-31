package com.home.shop.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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

    public Mono<com.home.shop.persistence.models.dynamodb.Product> retrieveProductByIDynamoDB(long productArticle) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":article", new AttributeValue().withN(String.valueOf(productArticle)));
        DynamoDBScanExpression dynamoDBQueryExpression = new DynamoDBScanExpression()
                .withFilterExpression("productArticle = :article")
                .withExpressionAttributeValues(eav);
        List<com.home.shop.persistence.models.dynamodb.Product> products =
                dynamoDBMapper.scan(com.home.shop.persistence.models.dynamodb.Product.class, dynamoDBQueryExpression);
        Optional<com.home.shop.persistence.models.dynamodb.Product> product = products.stream().findFirst();
        return product.map(Mono::just).orElseGet(Mono::empty);
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public void saveDynamoDb(ProductCreateRequest productRequest) {
        dynamoDBMapper.save(com.home.shop.persistence.models.dynamodb.Product.builder()
                .article(productRequest.getProductArticle())
                .productName(productRequest.getProductName())
                .build());
    }
}
