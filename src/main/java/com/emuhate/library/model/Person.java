package com.emuhate.library.model;

public abstract class Person {

    protected String name;
    protected String email;

    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
