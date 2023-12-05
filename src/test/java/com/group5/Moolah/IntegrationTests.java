package com.group5.Moolah;

import com.group5.Moolah.model.*;

import com.group5.Moolah.repositories.Constants;
import com.group5.Moolah.repositories.UserAuthManager;
import com.group5.Moolah.repositories.UserDataManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.group5.Moolah.repositories.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class IntegrationTests {
    MongoClient client;

    @BeforeClass
    public static void initIntegrationTests() {

    }

    @Test(timeout = 5000)
    public void signupLogin() {
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager ud = new UserDataManager(client);

        ud.deleteUser("fhagan2@gmu.edu");

        assertTrue(UserAuthManager.userSignup("Fletcher", "fhagan2@gmu.edu", "password"));
        assertTrue(UserAuthManager.userLogin("Fletcher", "fhagan2@gmu.edu", "password"));
        assertTrue(ud.matchUser("Fletcher", "fhagan2@gmu.edu", "password"));
        assertTrue(ud.deleteUser("fhagan2@gmu.edu"));
    }

    @Test
    public void addAndRemoveExpense() {
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");
        DeleteExpense d = new DeleteExpense("Boba", 6.25);
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d));
    }

    @Test
    public void addUpdateRemoveExpense() {
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");
        DeleteExpense d = new DeleteExpense("Boba", 7.25);
        UpdateExpense u = new UpdateExpense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun", "Boba", 7.25, "2023-12-10", "debit", "one-time", "fun");
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e));
        assertTrue(exp.updateExpense("fhagan2@gmu.edu", u));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d));
    }

    @Test
    public void addAndReturnRecentExpenses() {
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e1 = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e2 = new Expense("Soda", 7.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e3 = new Expense("Water", 8.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e4 = new Expense("Milk", 9.25, "2023-12-10", "credit", "one-time", "fun");

        List <Expense> expenseList = new ArrayList<>();
        expenseList.add(e4);
        expenseList.add(e3);
        expenseList.add(e2);

        assertTrue(exp.addExpense("fhagan2@gmu.edu", e1));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e2));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e3));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e4));

        //System.out.println(exp.retrieveDailyExpenses("fhagan2@gmu.edu", "2023-12-10"));
        List <Expense> retrieved = exp.findRecentDocuments("fhagan2@gmu.edu");
        assertTrue(expenseList.get(0).getName().equals(retrieved.get(0).getName()));
        assertTrue(expenseList.get(0).getAmount() == retrieved.get(0).getAmount());
        assertTrue(expenseList.get(1).getName().equals(retrieved.get(1).getName()));
        assertTrue(expenseList.get(1).getAmount() == retrieved.get(1).getAmount());
        assertTrue(expenseList.get(2).getName().equals(retrieved.get(2).getName()));
        assertTrue(expenseList.get(2).getAmount() == retrieved.get(2).getAmount());

        DeleteExpense d1 = new DeleteExpense("Boba", 6.25);
        DeleteExpense d2 = new DeleteExpense("Soda", 7.25);
        DeleteExpense d3 = new DeleteExpense("Water", 8.25);
        DeleteExpense d4 = new DeleteExpense("Milk", 9.25);

        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d1));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d2));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d3));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d4));
    }

    @Test
    public void addAndCalculateRecentExpenses() {
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);
        CalculationManager c = new CalculationManager(client);

        Expense e1 = new Expense("Boba", 6.25, "2020-12-10", "credit", "one-time", "fun");
        Expense e2 = new Expense("Soda", 7.25, "2020-12-10", "credit", "one-time", "fun");
        Expense e3 = new Expense("Water", 8.25, "2020-12-10", "credit", "one-time", "fun");
        Expense e4 = new Expense("Milk", 9.25, "2020-12-10", "credit", "one-time", "fun");

        assertTrue(exp.addExpense("fhagan2@gmu.edu", e1));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e2));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e3));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e4));

        System.out.println(c.getDayTotal("fhagan2@gmu.edu", "2020-12-10"));
        assertEquals(31.00, c.getDayTotal("fhagan2@gmu.edu", "2020-12-10"), 0.01);

        DeleteExpense d1 = new DeleteExpense("Boba", 6.25);
        DeleteExpense d2 = new DeleteExpense("Soda", 7.25);
        DeleteExpense d3 = new DeleteExpense("Water", 8.25);
        DeleteExpense d4 = new DeleteExpense("Milk", 9.25);

        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d1));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d2));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d3));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d4));
    }

    @Test
    public void returnDocsAtDate() {
        MongoClient client = MongoClients.create(Constants.URI);
        ExpenseDataManager exp = new ExpenseDataManager(client);

        Expense e1 = new Expense("Boba", 6.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e2 = new Expense("Soda", 7.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e3 = new Expense("Water", 8.25, "2023-12-10", "credit", "one-time", "fun");
        Expense e4 = new Expense("Milk", 9.25, "2023-12-10", "credit", "one-time", "fun");

        List <Expense> expenseList = new ArrayList<>();
        expenseList.add(e4);
        expenseList.add(e3);
        expenseList.add(e2);

        assertTrue(exp.addExpense("fhagan2@gmu.edu", e1));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e2));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e3));
        assertTrue(exp.addExpense("fhagan2@gmu.edu", e4));

        List <Expense> retrieved = exp.retrieveDailyExpenses("fhagan2@gmu.edu", "2023-12-10");
        assertTrue(expenseList.get(0).getName().equals(retrieved.get(0).getName()));
        assertTrue(expenseList.get(0).getAmount() == retrieved.get(0).getAmount());
        assertTrue(expenseList.get(1).getName().equals(retrieved.get(1).getName()));
        assertTrue(expenseList.get(1).getAmount() == retrieved.get(1).getAmount());
        assertTrue(expenseList.get(2).getName().equals(retrieved.get(2).getName()));
        assertTrue(expenseList.get(2).getAmount() == retrieved.get(2).getAmount());

        DeleteExpense d1 = new DeleteExpense("Boba", 6.25);
        DeleteExpense d2 = new DeleteExpense("Soda", 7.25);
        DeleteExpense d3 = new DeleteExpense("Water", 8.25);
        DeleteExpense d4 = new DeleteExpense("Milk", 9.25);

        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d1));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d2));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d3));
        assertTrue(exp.deleteExpense("fhagan2@gmu.edu", d4));
    }

}
