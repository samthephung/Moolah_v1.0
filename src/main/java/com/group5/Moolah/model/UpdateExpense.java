package com.group5.Moolah.model;

public class UpdateExpense {

    private String name;
    private double amount;

    //date is in the format: yyyy-mm-dd
    private String date;
    private String method;
    private String recurring;
    private String category;

    private String name_update;
    private double amount_update;

    //date is in the format: yyyy-mm-dd
    private String date_update;
    private String method_update;
    private String recurring_update;

    private String category_update;

    public UpdateExpense(String name, double amount, String date, String method, String recurring, String category,
                         String name_update, double amount_update, String date_update, String method_update, String recurring_update, String category_update){
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.method = method;
        this.recurring = recurring;

        this.name_update = name_update;
        this.date_update = date_update;
        this.amount_update = amount_update;
        this.category_update = category_update;
        this.method_update = method_update;
        this.recurring_update = recurring_update;

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

    public String getName_update() {
        return name_update;
    }

    public void setName_update(String name_update) {
        this.name_update = name_update;
    }

    public double getAmount_update() {
        return amount_update;
    }

    public void setAmount_update(double amount_update) {
        this.amount_update = amount_update;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public String getMethod_update() {
        return method_update;
    }

    public void setMethod_update(String method_update) {
        this.method_update = method_update;
    }

    public String getRecurring_update() {
        return recurring_update;
    }

    public void setRecurring_update(String recurring_update) {
        this.recurring_update = recurring_update;
    }

    public String getCategory_update() {
        return category_update;
    }

    public void setCategory_update(String category_update) {
        this.category_update = category_update;
    }


}
