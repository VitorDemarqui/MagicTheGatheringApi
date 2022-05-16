package com.zappts.magic_the_gathering_API.exception.jogadorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JogadorAlreadyRegisteredException extends Exception{
    public JogadorAlreadyRegisteredException() {
        super("Player already registered in the system.");
    }
}
