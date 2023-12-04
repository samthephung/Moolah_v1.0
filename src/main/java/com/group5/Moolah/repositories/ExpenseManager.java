package com.group5.Moolah.repositories;

import com.group5.Moolah.model.DeleteExpense;
import com.group5.Moolah.model.Expense;
import com.group5.Moolah.model.UpdateExpense;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.List;

public class ExpenseManager {

    //create mongo stuff in here too and then call the expense data manager classes appropriately
    public static Boolean addExpenseManager(String id, Expense e) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            //attempt to add a new expense
            return ed.addExpense(id, e);
        }
    }

    public static Boolean updateExpenseManager(String id, UpdateExpense u) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            return ed.updateExpense(id, u);
        }
    }

    public static Boolean deleteExpenseManager(String id, DeleteExpense d) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            //attempt to delete a new expense
            return ed.deleteExpense(id, d);
        }
    }

    public static List<Expense> retrieveRecentExpenseManager(String id) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            //retrieve an arraylist of the expense objects
            return ed.findRecentDocuments(id);
        }
    }

}
