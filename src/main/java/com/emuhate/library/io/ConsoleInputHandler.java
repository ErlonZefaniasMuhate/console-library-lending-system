package com.emuhate.library.io;

import java.util.Scanner;

public class ConsoleInputHandler {

    private static final ConsoleInputHandler instance = new ConsoleInputHandler();
    private final Scanner scanner;

    private ConsoleInputHandler(){
        scanner = new Scanner(System.in);
    }

    public static ConsoleInputHandler getInstance(){ return instance; }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public String readString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
