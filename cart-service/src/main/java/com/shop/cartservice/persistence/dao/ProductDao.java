package com.shop.cartservice.persistence.dao;

import java.util.List;
import java.util.Set;

import com.shop.cartservice.persistence.models.Product;
import com.shop.cartservice.persistence.repositories.ProductRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDao {

    private final ProductRepository productRepository;

    public List<Product> saveProducts(Set<Product> products) {
        return productRepository.saveAllAndFlush(products);
    }
}
