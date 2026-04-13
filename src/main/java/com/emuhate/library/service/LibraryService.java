package com.emuhate.library.service;

import com.emuhate.library.model.Book;
import com.emuhate.library.model.Borrowable;
import com.emuhate.library.model.Member;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private static List<Book> catalog = new ArrayList<>();
    static{

    }

    public static Book findBookByID(){
        return null;
    }

    public static void processCheckout(Borrowable item, Member member){}

    public static void processReturn(Borrowable item, Member member){}

}
