package com.zappts.magic_the_gathering_API.exception.listException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ListUserIsNotValidForOperationException extends Exception{
    public ListUserIsNotValidForOperationException() {
        super("User cannot perform this operation");
    }
}
