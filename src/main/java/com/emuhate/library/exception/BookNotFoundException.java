package com.emuhate.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {}

    @Override
    public String getMessage(){
        return "The book you are trying is not in the catalog. Please check the book ISBN and try again.";
    }
}
