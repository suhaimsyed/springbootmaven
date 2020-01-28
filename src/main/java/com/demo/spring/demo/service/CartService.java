package com.demo.spring.demo.service;

import com.demo.spring.demo.exception.CartEntryException;
import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import com.demo.spring.demo.dto.EntryDTO;
import com.demo.spring.demo.exception.EntryNotFoundException;
import com.demo.spring.demo.repository.CartEntryRepository;
import com.demo.spring.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartEntryRepository cartEntryRepository;

    private final CartEntryService cartEntryService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       CartEntryRepository cartEntryRepository,
                       CartEntryService cartEntryService) {
       this.cartRepository = cartRepository;
       this.cartEntryRepository = cartEntryRepository;
       this.cartEntryService = cartEntryService;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> getCartByCode(String code) {
        return cartRepository.findCartByCode(code);
    }

    public Optional<Entry> updateItemInCart(EntryDTO entryDTO) throws EntryNotFoundException {
        Optional<Entry> entry =  cartEntryService.updateItemInCart(entryDTO);
        recalculateCart(entry.get().getCart(), entry.get().getCart().getEntriesList());
        saveCart(entry.get().getCart());
        return entry;
    }

    public Optional<Cart> addItemToCart(EntryDTO entryDTO) throws CartEntryException {
        if(cartEntryService.isItemAlreadyInCart(entryDTO)) {
            throw new CartEntryException("Entry Already Present");
        }
        Cart cart = entryDTO.getCart();
        List<Entry> entries = cart.getEntriesList();
        entries.add(cartEntryService.addItemInCart(entryDTO));
        cart.setEntriesList(entries);
        recalculateCart(cart, entries);
        saveCart(cart);
        return Optional.of(cart);
    }

    public void recalculateCart(Cart cart, List<Entry> entries) {
        BigDecimal total = entries.stream().map(e-> e.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        total = total.setScale(2, RoundingMode.HALF_UP);
        cart.setTotal(total);
        cart.setSubtotal(total);
        cart.setDiscounts(new BigDecimal(0));
        BigDecimal tax = total.multiply(new BigDecimal(0.19)).setScale(2, RoundingMode.HALF_UP);
        cart.setTotalTax(tax);
    }


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }


}
