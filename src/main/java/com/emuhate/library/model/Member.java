package com.emuhate.library.model;

import com.emuhate.library.exception.BookNotAvailableException;
import com.emuhate.library.exception.MaxBooksExceededException;

import static com.emuhate.library.resources.IDGenerator.generateUUID;

public class Member extends Person{

    private final String memberID;
    private Book[] borrowedBooks;
    public static final int MAX_BOOKS_ALLOWED = 3;

    {
        memberID = generateUUID(name);
        borrowedBooks = new Book[MAX_BOOKS_ALLOWED];
    }

    public Member(String email, String name) {
        super(email, name);
    }

    @Override
    public String getDescription() {
        return "";
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) throws BookNotAvailableException, MaxBooksExceededException {
        boolean success = false;

        for (int i = 0; i < MAX_BOOKS_ALLOWED; i++) {
            if(this.borrowedBooks[i] == null){

                book.checkout(this);

                this.borrowedBooks[i] = book;
                return;
            }
        }

        if(!success){
            throw new MaxBooksExceededException("Member cannot borrow more than 3 books.");
        }
    }

    public void returnBook(Book book) {
        boolean sucess = false;

        for(int i = 0; i < MAX_BOOKS_ALLOWED; i++) {
            if(book.getIsbn().equals(borrowedBooks[i].getIsbn())){
                book.returnItem();
                borrowedBooks[i] = null;
                sucess = true;
            }
        }

        if(!sucess){
            throw new IllegalArgumentException("Member "
                .concat(memberID)
                .concat(" has not borrowed ")
                .concat(book.getTitle())
            );
        }
    }
}
