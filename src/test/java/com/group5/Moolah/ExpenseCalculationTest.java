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
        List<Integer> date1 = new ArrayList<>(){ {add(1); add(10); add(2023);} };
        List<Integer> date2 = new ArrayList<>(){ {add(12); add(10); add(2023);} };
        List<Integer> date3 = new ArrayList<>(){ {add(17); add(10); add(2023);} };

        //date -- February 24th 2023 (2/24/23) -- February Dates
        List<Integer> date4 = new ArrayList<>(){ {add(24); add(2); add(2023);} };
        //date -- August 23rd 2023 (8/23/2023) -- August Dates
        List<Integer> date5 = new ArrayList<>(){ {add(23); add(8); add(2023);} };


        //one user -- identified by their email -- sp@gmail.com
        Expense e = new Expense(date1, 25.00, "Take-out", "cash", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date2, 5.25, "Uber", "card", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date3, 3.05, "Chips", "cash", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date4, 125.12, "Groceries for the Week", "card", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date4, 45.28, "Books", "card", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date4, 44.02, "Top Golf", "card", false);
        exp.addExpense("sp@gmail.com", e);

        e = new Expense(date5, 35.00, "Cake", "cash", false);
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
        List<Integer> date = new ArrayList<>(){ {add(1); add(10); add(2023);} };

        assertEquals(25.00, ec.getDayTotal("sp@gmail.com", date), 0.01);
    }

    //get total of all expenses from a specific day and user -- handling multiple expenses
    @Test(timeout = 5000)
    public void getDayTotal_MultipleExpenses_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        List<Integer> date = new ArrayList<>(){ {add(24); add(2); add(2023);} };

        assertEquals(214.42, ec.getDayTotal("sp@gmail.com", date), 0.01);

    }

    //no expenses exist on the specified date = 0
    @Test(timeout = 5000)
    public void getDayTotal_NoExpenses_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        List<Integer> date = new ArrayList<>(){ {add(5); add(2); add(2023);} };

        assertEquals(0, ec.getDayTotal("sp@gmail.com", date), 0.01);

    }

    //get total of all expenses from a specific month and user -- handle a single expense
    @Test(timeout = 5000)
    public void getMonthTotal_SingleExpense_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        List<Integer> date = new ArrayList<>(){ {add(23); add(8); add(2023);} };

        assertEquals(35.00, ec.getMonthTotal("sp@gmail.com", date), 0.01);

    }

    //get total of all expenses from a specific month and user -- handle multiple expenses
    @Test(timeout = 5000)
    public void getMonthTotal_MultipleExpenses_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        List<Integer> date = new ArrayList<>(){ {add(28); add(10); add(2023);} };

        assertEquals(33.30, ec.getMonthTotal("sp@gmail.com", date),  0.01);
    }

    //no expenses exist in the specified month = 0
    @Test(timeout = 5000)
    public void getMonthTotal_NoExpenses_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        CalculationManager ec = new CalculationManager(client);
        List<Integer> date = new ArrayList<>(){ {add(14); add(5); add(2023);} };

        assertEquals(0, ec.getMonthTotal("sp@gmail.com", date), 0.01);

    }

}
