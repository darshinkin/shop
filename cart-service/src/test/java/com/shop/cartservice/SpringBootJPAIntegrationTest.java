package com.shop.cartservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.cartservice.persistence.dao.CartDao;
import com.shop.cartservice.persistence.models.Cart;
import com.shop.cartservice.persistence.models.Product;
import com.shop.cartservice.persistence.repositories.CartRepository;
import com.shop.cartservice.persistence.repositories.ProductRepository;

@SpringBootTest
public class SpringBootJPAIntegrationTest {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenCartRepository_whenSaveAndRetreiveEntity_thenOK() {
        Cart cart = new Cart();
        Cart cartEntity = cartRepository.save(cart);
        Product p1 = Product.builder().productId(1L).productName("apple").cart(cart).build();
        Product p2 = Product.builder().productId(2L).productName("pear").cart(cart).build();
        Set<Product> products = Set.of(p1, p2);
        List<Product> savedProducts = productRepository.saveAllAndFlush(products);
        cart.setProducts(Set.copyOf(savedProducts));
        Cart foundEntity = cartRepository.findByCartId(cartEntity.getCartId());

        assertNotNull(foundEntity);
        assertEquals(cartEntity.getCartId(), foundEntity.getCartId());
        assertThat(savedProducts).hasSameSizeAs(products);
        assertThat(cartEntity.getProducts()).hasSameSizeAs(foundEntity.getProducts());
    }

    @Test
    public void whenCreateCart_thenReturnNewCart() {
        Cart cart = cartDao.createCart();

        assertNotNull(cart);
        assertNotNull(cart.getCartId());
        assertThat(cart.getProducts()).hasSize(0);
    }
}
