package com.emuhate.library.model;

public abstract class Person {

    protected String name;
    protected String email;

    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public abstract String getDescription();
}
