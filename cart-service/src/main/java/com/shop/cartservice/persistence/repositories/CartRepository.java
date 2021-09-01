package com.shop.cartservice.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.cartservice.persistence.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT c FROM Cart c LEFT JOIN FETCH c.products where c.cartId = :cartId")
    Cart findByCartId(@Param("cartId") long cartId);
}
