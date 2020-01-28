package com.demo.spring.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Entry Not Found")
public class CartEntryException extends Exception {
    public CartEntryException(String message) {
        super(message);
    }
}
