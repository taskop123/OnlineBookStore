package com.finkiwpproject.onlinebookstore.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class PhoneNumberNotFound extends RuntimeException{

    public PhoneNumberNotFound(String phoneNumber) {

        super(String.format("Phone number: %s, was not found!", phoneNumber));

    }

    public PhoneNumberNotFound(Long id) {

        super(String.format("Phone number with id: %d, was not found!", id));

    }

}
