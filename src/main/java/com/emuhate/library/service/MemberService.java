package com.emuhate.library.service;

import com.emuhate.library.exception.MemberNotFoundException;
import com.emuhate.library.io.ConsoleInputHandler;
import com.emuhate.library.model.Member;
import com.emuhate.library.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemberService {

    private final List<Member> members;

    private static final ConsoleInputHandler consoleInputHandlerInstance = ConsoleInputHandler.getInstance();
    private static final MemberService instance = new MemberService();

    private MemberService(){
        members = new ArrayList<>(List.of(
            new Member("John Doe", "john.doe@gmail.com"),
            new Member("Jane Smith", "jane.smith@firefox.com"),
            new Member("Alice Johnson", "alice.johnson@outlook.com"),
            new Member("Bob Brown", "bob.brown@yahoo.com"),
            new Member("Charlie Wilson", "charlie.wilson@protonmail.com")
        ));
    }

    public static MemberService getInstance(){ return instance; }

    public Member findByID(String memberID){
        for(Member member : members){
            if(member.getMemberID().equals(memberID)){
                return member;
            }
        }
        throw new MemberNotFoundException();
    }

    public void addMember(){
        String name = consoleInputHandlerInstance.readString("Enter member name: ");
        while(!Validator.isValidName(name)){
            System.err.println("Invalid name, valid names are letters-only. Please try again.");
            name = consoleInputHandlerInstance.readString("Enter member name: ");
        }

        String email = consoleInputHandlerInstance.readString("Enter member email: ");
        while(!Validator.isValidEmail(email)){
            System.err.println("Invalid email format. Please try again.");
            email = consoleInputHandlerInstance.readString("Enter member email: ");
        }

        members.add(new Member(name, email));
    }

    public List<Member> getAllMembers(){ return Collections.unmodifiableList(members); }

    public void displayMembers() {
        System.out.println("==============================");
        System.out.println("Library Members");
        System.out.println();
        for (Member member : members) {
            System.out.printf("ID: %s | Name: %s | Email: %s | Borrowed: %d books%n",
                    member.getMemberID(), member.getName(), member.getEmail(), member.getBorrowedBooksCount());
        }
        System.out.println("======= End of Members =======");
    }
}
