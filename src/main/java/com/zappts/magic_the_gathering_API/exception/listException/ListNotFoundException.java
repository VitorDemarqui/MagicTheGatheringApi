package com.zappts.magic_the_gathering_API.exception.listException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListNotFoundException extends Exception{
    public ListNotFoundException() {
        super("List with this id not found in the system.");
    }
}
