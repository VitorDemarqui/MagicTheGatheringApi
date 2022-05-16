package com.zappts.magic_the_gathering_API.exception.idiomaException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdiomaAlreadyRegisteredException extends Exception{
    public IdiomaAlreadyRegisteredException() {
        super("Idioma already registered in the system.");
    }
}
