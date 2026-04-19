package com.emuhate.library.service;

import com.emuhate.library.exception.BookNotFoundException;
import com.emuhate.library.model.Book;

import java.util.List;

public class BookService {
    private final List<Book> catalog;
    private static final BookService instance;

    static{
        instance = new BookService();
    }

    {
        catalog = List.of(
                new Book("978-0134685991", "Effective Java", "Joshua Bloch"),
                new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman"),
                new Book("978-1617294945", "Spring in Action", "Craig Walls"),
                new Book("978-0201633610", "Design Patterns", "Gang of Four"),
                new Book("978-0134494166", "Clean Code", "Robert C. Martin"),
                new Book("978-0596527341", "RESTful Web Services", "Leonard Richardson"),
                new Book("978-1491927281", "Building Microservices", "Sam Newman")
        );
    }

    private BookService(){}

    public static BookService getInstance() { return instance;}

    public Book findBookByISBN(String isbn) throws BookNotFoundException {
        if(isbn.matches("978-\\d{3}-\\d{10}")){
            for(Book book : catalog){
                if(book.getIsbn().equals(isbn)){
                    return book;
                }
            }
        }
        throw new BookNotFoundException();
    }

    public void displayCatalog(){
        System.out.println("==============================");
        System.out.println("Library Catalog");
        System.out.println();

        for(Book book : catalog){
            System.out.print("ISBN: " + book.getIsbn());
            System.out.print(", Title: " + book.getTitle());
            System.out.print(", Author: " + book.getAuthor());
            System.out.println();
        }

        System.out.println("======= End of Catalog =======");
    }
}
