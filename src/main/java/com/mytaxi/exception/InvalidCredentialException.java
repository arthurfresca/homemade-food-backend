package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User doesn't not exists or password is invalid...")
public class InvalidCredentialException extends Exception
{

    static final long serialVersionUID = 1L;


    public InvalidCredentialException(String message)
    {
        super(message);
    }

}
