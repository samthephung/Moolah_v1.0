package com.group5.Moolah.model;

import java.util.List;
public class Expense {

    private String name;
    private double amount;

    //date is in the format: yyyy-mm-dd
    private String date;
    private String method;
    private String recurring;
    private String category;

    public Expense(String name, double amount, String date, String method, String recurring, String category){
        System.out.println("Starting constructing expense");
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.method = method;
        this.recurring = recurring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRecurring() {
        return recurring;
    }

    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }
}

