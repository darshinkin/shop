package com.shop.product.routers.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long productArticle;
    private String productName;
}
