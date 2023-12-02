package com.group5.Moolah.model;

public class User {

    //each user will have a unique identifier
    //private int id;
    private String firstName;
    private String lastName;

    //username is the email address -- for logging in
    private String emailAddress;
    private String password;

    public User(String firstName, String lastName, String emailAddress, String password) {
        //this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
