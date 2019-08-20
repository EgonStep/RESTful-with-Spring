package com.rest.webservices.demo.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicateUserEntryException extends RuntimeException {
    public DuplicateUserEntryException(String message) {
        super(message);
    }
}
