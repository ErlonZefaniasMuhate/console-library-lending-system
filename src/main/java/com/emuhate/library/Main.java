package com.emuhate.library;

import com.emuhate.library.console.LibraryConsoleUI;
import com.emuhate.library.io.ConsoleInputHandler;
import com.emuhate.library.io.Logger;
import com.emuhate.library.service.BookService;
import com.emuhate.library.service.LibraryService;
import com.emuhate.library.service.MemberService;

@Deprecated
public class Main {

    public static void main(String[] args) {
        var input = new ConsoleInputHandler();
        new LibraryConsoleUI(
                new BookService(),
                new MemberService(input),
                input,
                new LibraryService(new Logger())
        ).run();
    }
}
