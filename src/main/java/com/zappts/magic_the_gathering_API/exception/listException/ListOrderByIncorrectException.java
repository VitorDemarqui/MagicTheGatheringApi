package com.zappts.magic_the_gathering_API.exception.listException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListOrderByIncorrectException extends Exception{
    public ListOrderByIncorrectException() {
        super("Order by Invalid");
    }
}
