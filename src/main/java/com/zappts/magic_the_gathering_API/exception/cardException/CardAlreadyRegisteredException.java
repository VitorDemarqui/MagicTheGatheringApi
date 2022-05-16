package com.zappts.magic_the_gathering_API.exception.cardException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardAlreadyRegisteredException extends Exception{
    public CardAlreadyRegisteredException() {
        super("Card already registered in the system.");
    }
}
