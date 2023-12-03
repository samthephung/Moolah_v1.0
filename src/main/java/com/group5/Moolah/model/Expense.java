package com.group5.Moolah.model;

import java.util.List;
public class Expense {

    //date is a list in the format: DAY, MONTH, YEAR (in the respective indexes 0,1,2)
    private String name;
    private List<Integer> date;
    private double amount;
    private String category;
    private String method;
    private Boolean recurring;

    public Expense(String name, List<Integer> date, double amount, String category, String method, Boolean recurring){
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

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }
}

