package com.demo.spring.demo.service;

import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import com.demo.spring.demo.repository.CartEntryRepository;
import com.demo.spring.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartEntryRepository cartEntryRepository;

    @Autowired
    public CartService(CartRepository cartRepository,CartEntryRepository cartEntryRepository) {
       this.cartRepository = cartRepository;
       this.cartEntryRepository = cartEntryRepository;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Entry saveCartEntry(Entry entry) {
        return cartEntryRepository.save(entry);
    }
}
