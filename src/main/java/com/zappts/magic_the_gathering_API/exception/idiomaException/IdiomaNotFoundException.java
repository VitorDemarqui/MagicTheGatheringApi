package com.zappts.magic_the_gathering_API.exception.idiomaException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdiomaNotFoundException extends Exception{

    public IdiomaNotFoundException() {
        super("Idioma with this id not found in the system.");
    }

}