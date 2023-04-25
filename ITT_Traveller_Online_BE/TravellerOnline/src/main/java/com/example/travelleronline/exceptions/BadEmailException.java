package com.example.travelleronline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.METHOD_FAILURE)
public class BadEmailException extends RuntimeException {
    public BadEmailException(String message) {
        super(message);
    }
}
