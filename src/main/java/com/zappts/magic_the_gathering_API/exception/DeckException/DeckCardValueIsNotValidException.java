package com.zappts.magic_the_gathering_API.exception.DeckException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeckCardValueIsNotValidException extends Exception{
    public DeckCardValueIsNotValidException() {
        super("Card value is not valid.");
    }
}
