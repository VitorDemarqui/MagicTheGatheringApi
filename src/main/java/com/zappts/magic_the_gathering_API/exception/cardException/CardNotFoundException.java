package com.zappts.magic_the_gathering_API.exception.cardException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends Exception{
    public CardNotFoundException() {
        super("Card with this id not found in the system.");
    }
}
