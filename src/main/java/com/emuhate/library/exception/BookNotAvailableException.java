package com.emuhate.library.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {}

    @Override
    public String getMessage(){
        return "The book you is either not available or does not exist. Please check the book ID and try again.";
    }
}
