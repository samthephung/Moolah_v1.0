package com.group5.Moolah.services;

import com.group5.Moolah.model.Expense;

public class ExpenseService {


    //acts as an abtraction between the controller and repository classes -- since the controller should only speak to the service layer (which are these classes)
    //these classes will call the repository methods that perform the create, update, retrieve and delete operations

    //return true if successful add
    public static Boolean addExpenseService(Expense e){
        return true;
    }

    //return arraylist of objects
    public static Boolean retrieveExpenseService(String emailAddress, String name){
        return true;
    }

    /*
    //return true if updated expense occurs
    public static Boolean updateExpenseService(){ return true;}

    //return true if delete expense is possible
    public static Boolean deleteExpenseService(){
        return true;
    }

    */

}
