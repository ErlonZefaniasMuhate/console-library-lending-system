package com.emuhate.library.service;

import com.emuhate.library.exception.BookNotAvailableException;
import com.emuhate.library.exception.BookNotFoundException;
import com.emuhate.library.exception.MaxBooksExceededException;
import com.emuhate.library.exception.MemberNotFoundException;
import com.emuhate.library.io.ConsoleInputHandler;
import com.emuhate.library.io.LogType;
import com.emuhate.library.io.Logger;
import com.emuhate.library.model.Book;
import com.emuhate.library.model.Borrowable;
import com.emuhate.library.model.Member;

public class LibraryService {

    private static final LibraryService instance = new LibraryService();

    private static final BookService bookServiceInstance;
    private static final MemberService memberServiceInstance;
    private static final ConsoleInputHandler consoleInputHandlerInstance;
    private static final Logger loggerInstance;

    static{
        bookServiceInstance = BookService.getInstance();
        memberServiceInstance = MemberService.getInstance();
        consoleInputHandlerInstance = ConsoleInputHandler.getInstance();
        loggerInstance = Logger.getInstance();
    }

    private LibraryService() {}

    public static LibraryService getInstance(){ return instance; }

    public void processCheckout(Borrowable item, Member member){
        boolean success = true;

        loggerInstance.logTransaction(LogType.SYSTEM, "Attempting to checkout: " + member.getName() + " borrowing " + ((Book) item).getTitle());

        try {
            //Current project borrows books, but this can be extended to other borrowable items in the future.
            //Then we can use Generics or Visitor pattern
            member.borrowBook((Book) item);
        } catch (BookNotAvailableException | MaxBooksExceededException e) {
            loggerInstance.logTransaction(LogType.SYSTEM,
                    "Failed: "
                    .concat(e.getMessage())
                    .concat(e.getCause() != null ? " Cause: " + e.getCause().getMessage() : ""));
            success = false;
        }

        if(success){
            loggerInstance.logTransaction(LogType.TRANSACTION, "Success: " + member.getName() + " borrowed " + ((Book) item).getTitle());
        }
    }

    public void processReturn(Borrowable item, Member member){
        boolean success = true;
        loggerInstance.logTransaction(LogType.SYSTEM, "Attempting to return: " + member.getName() + " returning " + ((Book) item).getTitle());

        try {
            member.returnBook((Book) item);
        } catch (IllegalArgumentException e) {
            loggerInstance.logTransaction(LogType.SYSTEM, "Failed: "
                    .concat(e.getMessage())
                    .concat(e.getCause() != null ? " Cause: " + e.getCause().getMessage() : ""));
            success = false;
        }

        if(success){
            loggerInstance.logTransaction(LogType.TRANSACTION, "Success: " + member.getName() + " returned " + ((Book) item).getTitle());
        }
    }

    public void run(){
        String menu = """
            ===== Library Menu =====
            1. Display Catalog
            2. Register Member
            3. Display Members
            4. Borrow Book
            5. Return Book
            6. Exit
            """;

        switch(consoleInputHandlerInstance.readInt(menu)){
            case 1 -> bookServiceInstance.displayCatalog();
            case 2 -> {
                memberServiceInstance.addMember();
                loggerInstance.logTransaction(
                        LogType.TRANSACTION, "New member registered: " +
                        memberServiceInstance
                                .getAllMembers()
                                .getLast()
                                .getName()
                );
            }
            case 3 -> memberServiceInstance.displayMembers();
            case 4 -> processCheckout(getBook(), getMember());
            case 5 -> processReturn(getBook(), getMember());
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid input. Please choose one of the available options.");
        }
    }

    private Member getMember() {
        String memberID = consoleInputHandlerInstance.readString("Enter member ID: ");
        Member member = null;
        while(member == null) {
            try {
                member = memberServiceInstance.findByID(memberID);
            } catch (MemberNotFoundException e) {
                loggerInstance.logTransaction(LogType.SYSTEM, "Failed: " + e.getMessage());
                System.err.println(e.getMessage() + " Please try again.");
                memberID = consoleInputHandlerInstance.readString("Enter member ID: ");
            }
        }
        return member;
    }

    private Book getBook() {
        String bookISBN = consoleInputHandlerInstance.readString("Enter book ID: ");
        Book book = null;
        while(book == null){
            try{
                book = bookServiceInstance.findBookByISBN(bookISBN);
            }catch(BookNotFoundException e){
                loggerInstance.logTransaction(LogType.SYSTEM, "Failed: " + e.getMessage());
                System.err.println(e.getMessage() + " Please try again.");
                bookISBN = consoleInputHandlerInstance.readString("Enter book ID: ");
            }
        }
        return book;
    }
}
