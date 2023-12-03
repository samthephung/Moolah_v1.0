package com.group5.Moolah;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import main.src.Constants;
import main.src.Expense;
import main.src.ExpenseData;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ExpenseDataTest {

    //missing fields will be handled by the controller logic//
    //remove all expenses once finished
    @AfterClass
    public static void removeExpenses_CleanUp(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        exp.removeAllExpenses();
    }

    //create new expense
    //insert a new document with userid, date, amount, description, method, recurring expense
    @Test(timeout = 5000)
    public void createExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(14); add(10); add(2023);} };
        Expense e = new Expense(date, 6.45, "Boba", "card", false);

        assertTrue(exp.addExpense("sp@gmail.com", e));
    }

    //create a new expense
    @Test(timeout = 5000)
    public void createExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(1); add(10); add(2023);} };
        Expense e = new Expense(date, 50.00, "Amazon Gift Card for Sister!", "card", false);

        assertTrue(exp.addExpense("sp@gmail.com", e));
    }

    //update existing expense
    @Test(timeout = 5000)
    public void updateExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(14); add(10); add(2023);} };
        Expense e = new Expense(date, 6.45, "Boba", "card", false);

        List<Integer> newDate = new ArrayList<>(){ {add(12); add(10); add(2023);} };
        Expense updateE = new Expense(newDate, 4.50, "Milk Tea", "cash", false);

        assertTrue(exp.updateExpense("sp@gmail.com", e, updateE));
    }

    //update an existing expense
    @Test(timeout = 5000)
    public void updateExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(1); add(10); add(2023);} };
        Expense e = new Expense(date, 50.00, "Amazon Gift Card for Sister!", "card", false);

        List<Integer> newDate = new ArrayList<>(){ {add(24); add(10); add(2023);} };
        Expense updateE = new Expense(newDate, 60.50, "Amazon Shopping", "cash", false);

        assertTrue(exp.updateExpense("sp@gmail.com", e, updateE));
    }

    //try to update an expense that does not exist - fail
    @Test(timeout = 5000)
    public void updateExpense_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);

        List<Integer> date = new ArrayList<>(){ {add(12); add(4); add(2022);} };
        Expense e = new Expense(date, 50.00, "Amazon Gift Card for Sister!", "card", false);

        List<Integer> newDate = new ArrayList<>(){ {add(24); add(10); add(2023);} };
        Expense updateE = new Expense(newDate, 70.50, "Shopping", "card", false);

        assertFalse(exp.updateExpense("sp@gmail.com", e, updateE));
    }


    //delete existing expense
    @Test(timeout = 5000)
    public void deleteExpense_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(24); add(2); add(2023);} };
        Expense e = new Expense(date, 20.45, "Cake", "card", false);
        exp.addExpense("sp@gmail.com", e);

        assertTrue(exp.deleteExpense("sp@gmail.com", e));
    }

    //delete an existing expense
    @Test(timeout = 5000)
    public void deleteExpense_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(5); add(2); add(2023);} };
        Expense e = new Expense(date, 18.70, "Earbuds", "cash", false);
        exp.addExpense("sp@gmail.com", e);

        assertTrue(exp.deleteExpense("sp@gmail.com", e));
    }

    //try to delete an expense that does not exist - fail
    @Test(timeout = 5000)
    public void deleteExpense_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseData exp = new ExpenseData(client);
        List<Integer> date = new ArrayList<>(){ {add(25); add(6); add(2023);} };
        Expense e = new Expense(date, 7.12, "Starbucks", "card", false);

        assertFalse(exp.deleteExpense("sp@gmail.com", e));
    }
}
