package com.group5.Moolah.model;

public class User {

    //private int id -- unique id is the user's email
    private String name;

    //username is the email address -- for logging in
    private String emailAddress;
    private String password;

    public User(String name, String emailAddress, String password) {
        //this.id = 0;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
