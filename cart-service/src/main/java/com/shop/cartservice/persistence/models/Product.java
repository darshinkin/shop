package com.shop.cartservice.persistence.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long productId;

    private String productName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    private void setProductId(long productId) {
        this.productId = productId;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCart(Cart newCart) {
        if (Objects.equals(cart, newCart)) {
            return;
        }
        Cart oldCart = this.cart;
        this.cart = newCart;
        if (oldCart != null)
            oldCart.removeProduct(this);
        if (newCart != null)
            newCart.addProduct(this);
    }
}
