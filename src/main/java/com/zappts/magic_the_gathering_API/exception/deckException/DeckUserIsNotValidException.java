package com.zappts.magic_the_gathering_API.exception.deckException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DeckUserIsNotValidException extends Exception{
    public DeckUserIsNotValidException() {
        super("User cannot perform this operation");
    }
}
