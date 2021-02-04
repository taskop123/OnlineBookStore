package com.finkiwpproject.onlinebookstore.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TitleNotFoundException extends RuntimeException{

    public TitleNotFoundException(String title){
        super(String.format("Book with title: %s, was not found!", title));
    }

}
