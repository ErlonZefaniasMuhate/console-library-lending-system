package com.emuhate.library.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {}

    @Override
    public String getMessage(){
        return "The member you are trying to find does not exist. Please check the member ID and try again.";
    }
}
