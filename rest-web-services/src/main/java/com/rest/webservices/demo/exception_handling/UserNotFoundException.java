package com.rest.webservices.demo.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// We handle exception because the request will send HTTP Status code as 200
// which is wrong in case there isn`t a user with the wrong id
@ResponseStatus(HttpStatus.NOT_FOUND) // Will send HTTP Status Code 404
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
