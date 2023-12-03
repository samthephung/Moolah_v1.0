package com.group5.Moolah.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ExpenseManager {

    //create mongo stuff in here too and then call the expense data manager classes appropriately
    public static Boolean userSignup(String name, String email, String password) {
        try (MongoClient client = MongoClients.create(Constants.URI)) {
            ExpenseDataManager ud = new ExpenseDataManager(client);

            //in progress rn
            return true;
        }
    }



}
