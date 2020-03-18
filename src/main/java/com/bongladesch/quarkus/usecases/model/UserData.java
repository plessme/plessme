package com.bongladesch.quarkus.usecases.model;

public class UserData {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserData(final String email, final String password, final String firstName, final String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}