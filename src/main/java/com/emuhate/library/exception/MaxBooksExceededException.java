package com.emuhate.library.exception;

public class MaxBooksExceededException extends RuntimeException {
    public MaxBooksExceededException(String message) {
        super(message);
    }
}
