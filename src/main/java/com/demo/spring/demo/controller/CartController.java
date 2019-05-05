package com.demo.spring.demo.controller;

import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/api/carts/{id}")
    public Optional<Cart> findCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/api/carts")
    public List<Cart> findAllCarts() {
        return cartService.getCarts();
    }
}
