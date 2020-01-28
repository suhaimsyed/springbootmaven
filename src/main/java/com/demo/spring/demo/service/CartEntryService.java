package com.demo.spring.demo.service;

import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import com.demo.spring.demo.dto.EntryDTO;
import com.demo.spring.demo.exception.CartEntryException;
import com.demo.spring.demo.exception.EntryNotFoundException;
import com.demo.spring.demo.repository.CartEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CartEntryService {

    private final CartEntryRepository cartEntryRepository;

    @Autowired
    public CartEntryService(CartEntryRepository cartEntryRepository) {
        this.cartEntryRepository = cartEntryRepository;
    }

    public Boolean isItemAlreadyInCart(EntryDTO entryDTO) {
        return cartEntryRepository.findCartEntryByCode(entryDTO.getCode()).isPresent();
    }

    public Optional<Entry> updateItemInCart(EntryDTO entryDTO) throws EntryNotFoundException {
        Optional<Entry> entry = cartEntryRepository.findCartEntryByCode(entryDTO.getCode());
        if(!entry.isPresent()) {
            throw new EntryNotFoundException("Entry not found");
        }
        entry.get().setQuantity(entryDTO.getQuantity());
        entry.get().setTotal(entryDTO.getPrice().multiply(BigDecimal.valueOf(entryDTO.getQuantity()))
                               .setScale(2, RoundingMode.HALF_UP));
        saveCartEntry(entry.get());
        return entry;
    }

    public Entry addItemInCart(EntryDTO entryDTO) {
        Cart cart = entryDTO.getCart();
        Entry entry = new Entry();
        entry.setCart(cart);
        entry.setCode(entryDTO.getCode());
        entry.setQuantity(entryDTO.getQuantity());
        entry.setTotal(entryDTO.getPrice().multiply(BigDecimal.valueOf(entryDTO.getQuantity()))
                               .setScale(2, RoundingMode.HALF_UP));
        saveCartEntry(entry);
        return entry;
    }

    public Entry saveCartEntry(Entry entry) {
        return cartEntryRepository.save(entry);
    }
}
