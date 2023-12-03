package com.group5.Moolah.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class UserAuth {
    public static Boolean userSignup(String name, String email, String password) {
        try (MongoClient client = MongoClients.create(Constants.URI)) {
            UserData ud = new UserData(client);

            return ud.createUser(name, email, password);
        }
    }

    public static Boolean userLogin(String name, String email, String password) {
        try (MongoClient client = MongoClients.create(Constants.URI)) {
            UserData ud = new UserData(client);

            return ud.matchUser(name, email, password);
        }
    }
}
