package com.deepak.codeassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends Exception {

    public OfferNotFoundException() {
    }

    public OfferNotFoundException(String message) {
        super(message);
    }

}
