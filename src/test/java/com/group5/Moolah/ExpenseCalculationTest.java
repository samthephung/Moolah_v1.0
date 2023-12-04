package com.group5.Moolah;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.group5.Moolah.model.*;
import com.group5.Moolah.repositories.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class ExpenseCalculationTest {

    //create a new user and prepopulate expenses for each test
    @BeforeClass
    public static void populateWithExpenses(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        //date -- October 1st 2023 (10/1/23) -- October Dates
        String date1 = "2023-10-01";
        String date2 = "2023-10-12";
        String date3 = "2023-10-17";
        //date -- February 24th 2023 (2/24/23) -- February Dates
        String date4 = "2023-02-24";
        //date -- August 23rd 2023 (8/23/2023) -- August Dates
        String date5 = "2023-08-23";


        //one user -- identified by their email -- sp@gmail.com
        Expense e = new Expense("Take-out", 25.00, date1, "cash", "one-time", "food");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Uber", 5.25, date2, "card", "weekly", "travel");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Chips", 3.05, date3, "cash", "one-time", "food");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Groceries for the Week", 125.12, date4, "card", "weekly", "groceries");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Books", 45.28, date4, "card", "monthly", "fun");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Top Golf", 44.02, date4, "card", "one-time", "fun");
        exp.addExpense("sp@gmail.com", e);

        e = new Expense("Cake", 35.00, date5, "cash", "yearly", "food");
        exp.addExpense("sp@gmail.com", e);
    }

    //delete all expenses once finished
    @AfterClass
    public static void removeExpenses_CleanUp(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);
        exp.removeAllExpenses();
    }

    //get total of all expenses from a specific day and user -- handle a single expense
    @Test(timeout = 5000)
    public void getDayTotal_SingleExpense_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2023-10-01";


        assertEquals(25.00, ec.getDayTotal("sp@gmail.com", date), 0.01);
    }

    //get total of all expenses from a specific day and user -- handling multiple expenses
    @Test(timeout = 5000)
    public void getDayTotal_MultipleExpenses_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2023-02-24";

        assertEquals(214.42, ec.getDayTotal("sp@gmail.com", date), 0.01);

    }

    //no expenses exist on the specified date = 0
    @Test(timeout = 5000)
    public void getDayTotal_NoExpenses_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2020-12-12";

        assertEquals(0, ec.getDayTotal("sp@gmail.com", date), 0.01);

    }

    //get total of all expenses from a specific month and user -- handle a single expense
    /*
    @Test(timeout = 5000)
    public void getMonthTotal_SingleExpense_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2023-08-23";

        assertEquals(35.00, ec.getMonthTotal("sp@gmail.com", date), 0.01);

    }

    //get total of all expenses from a specific month and user -- handle multiple expenses
    @Test(timeout = 5000)
    public void getMonthTotal_MultipleExpenses_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2023-10-28";

        assertEquals(33.30, ec.getMonthTotal("sp@gmail.com", date),  0.01);
    }

    //no expenses exist in the specified month = 0
    @Test(timeout = 5000)
    public void getMonthTotal_NoExpenses_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        String date = "2023-05-14";

        assertEquals(0, ec.getMonthTotal("sp@gmail.com", date), 0.01);

    }
     */



}
