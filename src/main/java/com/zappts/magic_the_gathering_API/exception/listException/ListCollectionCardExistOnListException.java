package com.zappts.magic_the_gathering_API.exception.listException;

public class ListCollectionCardExistOnListException extends Exception{
    public ListCollectionCardExistOnListException() {
        super("Card with this id not found in the List.");
    }
}
