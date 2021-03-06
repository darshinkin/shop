package com.shop.product.persistence.models.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@DynamoDBTable(tableName="Product")
public class Product {

    private String id;
    private Long article;
    private String productName;

    public Product(String id, Long article, String productName) {
        this.id = id;
        this.article = article;
        this.productName = productName;
    }

    public Product() {
    }

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "productArticle")
    public Long getArticle() {
        return article;
    }
    public void setArticle(Long article) {
        this.article = article;
    }

    @DynamoDBAttribute(attributeName = "productName")
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
