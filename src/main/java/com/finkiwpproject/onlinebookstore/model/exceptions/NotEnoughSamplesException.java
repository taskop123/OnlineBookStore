package com.finkiwpproject.onlinebookstore.model.exceptions;

public class NotEnoughSamplesException extends RuntimeException{

    public NotEnoughSamplesException(int howMuch, String title){
        super(String.format("We do not have %d samples from the book: '%s'", howMuch, title));
    }

}
