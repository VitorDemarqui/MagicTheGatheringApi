package com.zappts.magic_the_gathering_API.exception.DeckException;

public class DeckNotFoundException extends Exception{
    public DeckNotFoundException() {
        super("Deck with this id not found in the system.");
    }
}
