package com.emuhate.library.model;

import com.emuhate.library.exception.BookNotAvailableException;

public interface Borrowable {

    public void checkout(Member member) throws BookNotAvailableException;
    public void returnItem();
}
