package com.emuhate.library.main;

import com.emuhate.library.service.LibraryService;

public class Main {

    public static void main(String[] args) {
        LibraryService.getInstance().run();
    }
}
