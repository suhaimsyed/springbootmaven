package com.demo.spring.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cart Not Found")
public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }
}
