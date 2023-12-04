package com.group5.Moolah.repositories;

import com.group5.Moolah.model.DeleteExpense;
import com.group5.Moolah.model.Expense;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ExpenseManager {

    //create mongo stuff in here too and then call the expense data manager classes appropriately
    public static Boolean addExpenseManager(String id, Expense e) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            //attempt to add a new expense
            return ed.addExpense(id, e);
        }
    }

    public static Boolean deleteExpenseManager(String id, DeleteExpense d) {

        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ed = new ExpenseDataManager(client);
            //attempt to add a new expense
            return ed.deleteExpense(id, d);
        }
    }



}
