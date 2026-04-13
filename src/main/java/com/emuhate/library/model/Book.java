package com.emuhate.library.model;

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
    public void checkOut(Member member){}

    @Override
    public void returnItem(){};

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
