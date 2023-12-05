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

        UserAuthManager.userSignup("Fletcher", "fhagan2@gmu.edu", "password");
        UserAuthManager.userLogin("Fletcher", "fhagan2@gmu.edu", "password");
        assertTrue(ud.matchUser("Fletcher", "fhagan2@gmu.edu", "password"));
    }

}
