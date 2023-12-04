package com.group5.Moolah;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.group5.Moolah.model.*;
import com.group5.Moolah.repositories.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ExpenseDataTest {

    //missing fields will be handled by the controller logic//
    //remove all expenses once finished
    @AfterClass
    public static void removeExpenses_CleanUp(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);
        exp.removeAllExpenses();
    }

    //create new expense
    //insert a new document with userid, date, amount, description, method, recurring expense
    @Test(timeout = 5000)
    public void createExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");

        assertTrue(exp.addExpense("sp@gmail.com", e));
    }

    //create a new expense
    @Test(timeout = 5000)
    public void createExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Air Jordans", 300.5, "2020-05-06", "debit", "weekly", "gift");

        assertTrue(exp.addExpense("sp@gmail.com", e));
    }

    //update existing expense
    @Test(timeout = 5000)
    public void updateExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");

        UpdateExpense u = new UpdateExpense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun",
                "Boba", 7.25, "2023-12-10", "debit", "one-time", "food");

        assertTrue(exp.updateExpense("sp@gmail.com", u));
    }

    //update an existing expense
    @Test(timeout = 5000)
    public void updateExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Air Jordans", 300.5, "2020-05-06", "debit", "weekly", "gift");

        UpdateExpense u = new UpdateExpense("Air Jordans", 300.5, "2020-05-06", "debit", "weekly", "gift",
                "Air Jordans", 310.5, "2023-12-10", "debit", "one-time", "fun");

        assertTrue(exp.updateExpense("sp@gmail.com", u));
    }

    //try to update an expense that does not exist - fail
    @Test(timeout = 5000)
    public void updateExpense_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Nike Air Mags", 40000, "2020-05-06", "debit", "one-time", "gift");

        UpdateExpense u = new UpdateExpense("Nike SB Dunk Lows", 300.5, "2020-05-06", "debit", "weekly", "gift",
                "Nike SB Dunk Highs", 310.5, "2020-05-06", "debit", "one-time", "fun");

        assertFalse(exp.updateExpense("sp@gmail.com", u));
    }


    //delete existing expense
    @Test(timeout = 5000)
    public void deleteExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Hydraulic Press", 2.95, "2018-01-11", "credit", "weekly", "fun");
        DeleteExpense d = new DeleteExpense("Hydraulic Press", 2.95);
        exp.addExpense("sp@gmail.com", e);

        assertTrue(exp.deleteExpense("sp@gmail.com", d));
    }

    //delete an existing expense
    @Test(timeout = 5000)
    public void deleteExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Street Fighter 6", 59.99, "2023-05-14", "debit", "one-time", "fun");
        DeleteExpense d = new DeleteExpense("Street Fighter 6", 59.99);
        exp.addExpense("sp@gmail.com", e);

        assertTrue(exp.deleteExpense("sp@gmail.com", d));
    }

    //try to delete an expense that does not exist - fail
    @Test(timeout = 5000)
    public void deleteExpense_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Fletcher Hagan Magazine", 10.95, "2015-02-16", "credit", "monthly", "food");
        DeleteExpense d = new DeleteExpense("Fletcher Hagan Action Figure", 50.25);
        exp.addExpense("sp@gmail.com", e);

        assertFalse(exp.deleteExpense("sp@gmail.com", d));
    }
}
