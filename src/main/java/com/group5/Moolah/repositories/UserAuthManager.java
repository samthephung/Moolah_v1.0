package com.group5.Moolah.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class UserAuthManager {
    public static Boolean userSignup(String name, String email, String password) {
        try (MongoClient client = MongoClients.create(Constants.URI)) {
            UserDataManager ud = new UserDataManager(client);

            return ud.createUser(name, email, password);
        }
    }

    public static Boolean userLogin(String name, String email, String password) {
        try (MongoClient client = MongoClients.create(Constants.URI)) {
            UserDataManager ud = new UserDataManager(client);

            return ud.matchUser(name, email, password);
        }
    }
}
