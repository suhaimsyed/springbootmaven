package com.demo.spring.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cart Entry Not Found")
public class EntryNotFoundException extends Exception {
    public EntryNotFoundException(String message) {
        super(message);
    }
}
