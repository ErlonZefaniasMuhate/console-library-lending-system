package com.emuhate.library.exception;

public class MaxBooksExceededException extends RuntimeException {
    public MaxBooksExceededException() {}

    @Override
    public String getMessage(){
        return "Member already borrowed the maximum number of books allowed. Please return a book before borrowing another one.";
    }
}
