package com.shop.product.routers;

import com.shop.product.config.ProductConfig;
import com.shop.product.handlers.ProductHandlers;
import com.shop.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.server.*;

@Configuration
@Import(value = {ProductConfig.class})
public class Routers {

    @Value("${app.api.endpoint.product.retrieve_by_id}")
    private String uriGetProduct;

    @Value("${app.api.endpoint.product.create_product}")
    private String uriCreateProduct;

    @Autowired
    private ProductService productService;

    @Bean
    public RouterFunction<ServerResponse> productRouter() {
        RequestPredicate getProduct = RequestPredicates
                .GET(this.uriGetProduct);
        RequestPredicate createProduct = RequestPredicates
                .POST(this.uriCreateProduct);
        return RouterFunctions
                .route(getProduct, productHandlers()::getProductById)
                .andRoute(createProduct, productHandlers()::createProduct);
    }

    @Bean
    public ProductHandlers productHandlers() {
        return new ProductHandlers(productService);
    }
}
