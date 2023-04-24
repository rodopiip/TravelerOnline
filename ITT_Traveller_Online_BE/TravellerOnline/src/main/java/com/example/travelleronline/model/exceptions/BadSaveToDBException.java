package com.example.travelleronline.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
public class BadSaveToDBException extends RuntimeException{
    public BadSaveToDBException(String message) {
        super(message);
    }
}
