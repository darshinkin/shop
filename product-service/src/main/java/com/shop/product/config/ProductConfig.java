package com.shop.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shop.product.persistence.dao.ProductDao;
import com.shop.product.persistence.repositories.ProductRepository;
import com.shop.product.services.ProductService;

@Configuration
public class ProductConfig {

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public ProductService productService() {
        return new ProductService(productDao());
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDao(productRepository, dynamoDBMapper());
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }
}
