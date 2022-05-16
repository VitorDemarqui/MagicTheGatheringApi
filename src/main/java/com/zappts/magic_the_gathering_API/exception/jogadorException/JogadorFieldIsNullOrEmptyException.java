package com.zappts.magic_the_gathering_API.exception.jogadorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JogadorFieldIsNullOrEmptyException extends Exception{
    public JogadorFieldIsNullOrEmptyException(String field) {
        super(String.format("Field %s cannot be null or empty.", field));
    }
}
