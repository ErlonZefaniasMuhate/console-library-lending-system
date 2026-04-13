package com.emuhate.library.model;

public interface Borrowable {

    public void checkOut(Member member);
    public void returnItem();
}
