package com.zappts.magic_the_gathering_API.exception.deckException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeckNotFoundException extends Exception{
    public DeckNotFoundException() {
        super("Deck with this id not found in the system.");
    }
}
