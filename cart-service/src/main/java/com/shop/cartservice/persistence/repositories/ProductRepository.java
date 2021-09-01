package com.shop.cartservice.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.cartservice.persistence.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
