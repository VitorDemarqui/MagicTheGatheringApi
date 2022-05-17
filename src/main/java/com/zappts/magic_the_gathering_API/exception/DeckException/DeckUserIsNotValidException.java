package com.zappts.magic_the_gathering_API.exception.DeckException;

public class DeckUserIsNotValidException extends Exception{
    public DeckUserIsNotValidException() {
        super("User cannot perform this operation");
    }
}
