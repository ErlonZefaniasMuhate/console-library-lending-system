package com.emuhate.library.model;

import com.emuhate.library.exception.BookNotAvailableException;

public class Book implements Borrowable{

    private final String isbn;
    private final String title;
    private final String author;
    private BookStatus status;
    private Member currentBorrower;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
        this.currentBorrower = null;
    }

    @Override
    public void checkout(Member member) throws BookNotAvailableException {
        //Check if book can be borrowed
        if(this.status != BookStatus.AVAILABLE){
            throw new BookNotAvailableException("Book not available to borrow.");
        }

        //Borrow book
        this.currentBorrower = member;
        this.status = BookStatus.BORROWED;
    }

    @Override
    public void returnItem(){
        status = BookStatus.AVAILABLE;
        currentBorrower = null;
    };

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Member getCurrentBorrower() {
        return currentBorrower;
    }

    public void setCurrentBorrower(Member currentBorrower) {
        this.currentBorrower = currentBorrower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{\n");
        sb.append("    isbn='").append(isbn).append("',\n");
        sb.append("    title='").append(title).append("',\n");
        sb.append("    author='").append(author).append("',\n");
        sb.append("    status=").append(status).append(",\n");
        sb.append("    currentBorrower=").append(currentBorrower).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
