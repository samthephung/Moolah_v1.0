package com.group5.Moolah.services;// References for serialization: https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/data-formats/document-data-format-pojo/

public class User {
    private int id;
    private String name;
    private String emailAddress;
    private String password;

    public User(int id, String name, String emailAddress, String password) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() { return password; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) { this.password = password; }
}
