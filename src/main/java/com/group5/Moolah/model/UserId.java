package com.group5.Moolah.model;

public class UserId {

    private String userIdentifier;
    public UserId(String userIdentifier){
        this.userIdentifier = userIdentifier;
    }

    public UserId(){ this.userIdentifier = "";}

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    private String chosenDate;

    public void setChosenDate(String chosenDate) { this.chosenDate = chosenDate; }

    public String getChosenDate() { return chosenDate; }

}
