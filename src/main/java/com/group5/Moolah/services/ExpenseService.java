package com.group5.Moolah.services;

import com.group5.Moolah.model.DeleteExpense;
import com.group5.Moolah.model.Expense;
import com.group5.Moolah.model.UpdateExpense;
import com.group5.Moolah.repositories.ExpenseManager;

import java.util.List;

public class ExpenseService {


    //acts as an abtraction between the controller and repository classes -- since the controller should only speak to the service layer (which are these classes)
    //these classes will call the repository methods that perform the create, update, retrieve and delete operations

    //return true if successful add
    public static Boolean addExpenseService(String id, Expense e){
        return ExpenseManager.addExpenseManager(id, e);
    }

    public static Boolean updateExpenseService(String id, UpdateExpense u){
        return ExpenseManager.updateExpenseManager(id,u);
    }

    public static Boolean deleteExpenseService(String id, DeleteExpense d){
        return ExpenseManager.deleteExpenseManager(id, d);
    }

    //return arraylist of objects -- return all current expenses for a specific user
    public static List<Expense> retrieveRecentExpenseService(String id){
        return ExpenseManager.retrieveRecentExpenseManager(id);
    }


}
