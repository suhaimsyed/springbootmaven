package com.demo.spring.demo.controller;

import com.demo.spring.demo.exception.CartEntryException;
import com.demo.spring.demo.exception.CartNotFoundException;
import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import com.demo.spring.demo.dto.EntryDTO;
import com.demo.spring.demo.exception.EntryNotFoundException;
import com.demo.spring.demo.service.CartEntryService;
import com.demo.spring.demo.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CartController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private CartService cartService;

    private CartEntryService cartEntryService;

    @Autowired
    public CartController(CartService cartService,CartEntryService cartEntryService) {
        this.cartService = cartService;
        this.cartEntryService = cartEntryService;
    }

    @GetMapping("/api/carts/{code}")
    public Optional<Cart> findCartById(@PathVariable String code) {
        return cartService.getCartByCode(code);
    }

    @GetMapping("/api/carts")
    public List<Cart> findAllCarts() {
        return cartService.getCarts();
    }

    @PostMapping("/api/carts/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Cart> createNewCart() {
        Cart cart = new Cart();
        cart.setCode("Cart-"+ UUID.randomUUID().toString());
        cartService.saveCart(cart);
        return Optional.of(cart);
    }

    @PostMapping("/api/carts/{code}/entries")
    public Optional<Cart> addItemToCart(@PathVariable String code, @RequestBody EntryDTO entryDTO)
        throws CartNotFoundException, CartEntryException {
        getAndPrepareCart(code, entryDTO);
        return cartService.addItemToCart(entryDTO);

    }

    @PutMapping("/api/carts/{code}/entries")
    public Optional<Entry> updateItem(@PathVariable String code, @RequestBody EntryDTO entryDTO)
        throws CartNotFoundException, EntryNotFoundException {
        getAndPrepareCart(code, entryDTO);
        return cartService.updateItemInCart(entryDTO);
    }


    private void getAndPrepareCart(@PathVariable String code,
                                   @RequestBody EntryDTO entryDTO) throws CartNotFoundException {
        Optional<Cart> cart = cartService.getCartByCode(code);
        if (!cart.isPresent()) {
            throw new CartNotFoundException("Cart Not Found");
        }
        entryDTO.setCart(cart.get());
    }

}

