package com.shop.cartservice.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.cartservice.persistence.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
