package com.group5.Moolah.model;

import java.util.List;
public class Expense {

    //date is a list in the format: DAY, MONTH, YEAR (in the respective indexes 0,1,2)
    private List<Integer> date;
    private double amount;
    private String description;
    private String method;
    private Boolean recurring;

    public Expense(List<Integer> date, double amount, String description, String method, Boolean recurring){
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.method = method;
        this.recurring = recurring;
    }

    public List<Integer> getDate() {
        return date;
    }

    public void setDate(List<Integer> date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }
}

