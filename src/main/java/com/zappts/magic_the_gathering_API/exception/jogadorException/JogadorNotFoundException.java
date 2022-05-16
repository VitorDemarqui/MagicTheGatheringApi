package com.zappts.magic_the_gathering_API.exception.jogadorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JogadorNotFoundException extends Exception {

    public JogadorNotFoundException() {
        super("Player with this id not found in the system.");
    }

}