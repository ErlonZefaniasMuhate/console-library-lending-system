package com.emuhate.library.model;

import com.emuhate.library.exception.MaxBooksExceededException;

import static com.emuhate.library.resources.IDGenerator.generateUUID;

public class Member extends Person{

    private final String memberID;
    private Book[] borrowedBooks;
    public static final int MAX_BOOKS_ALLOWED = 3;

    {
        memberID = generateUUID(name);
    }

    public Member(String email, String name) {
        super(email, name);
    }

    @Override
    public String getDescription() {
        return "";
    }

    public void borrowBook(Book book) throws MaxBooksExceededException {
    }

    public void returnBook(Book book) {
    }
}
