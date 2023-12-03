package com.group5.Moolah;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.group5.Moolah.repositories.*;

import org.junit.*;
import static org.junit.Assert.*;

public class UserDataTest {

    //missing fields will be handled by the controller logic//
    //make sure to delete all data after each test
    @AfterClass
    public static void removeUsers_CleanUp(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);
        user.removeAllUsers();
    }

    //create new user
    //insert a new document with name, pass, email
    @Test(timeout = 5000)
    public void createUser_Success1(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Zena Baryoun";
        String email = "zb@gmail.com";
        String password = "12345";

        assertTrue(user.createUser(name, email, password));
    }

    //create a new user -- insert a new document with name, pass and email, budget=0
    @Test(timeout = 5000)
    public void createUser_Success2(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Fletcher Hagan";
        String email = "fh@gmail.com";
        String password = "12345";

        assertTrue(user.createUser(name, email, password));
    }

    //try to add a new user with email that is already in use -- fail
    @Test(timeout = 5000)
    public void createUser_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Jonathan Hart";
        String email = "jh@gmail.com";
        String password = "12345";
        user.createUser(name, email, password);

        assertFalse(user.createUser(name, email, password));
    }

    @Test(timeout = 5000)
    public void matchUser_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Fletcher Hagan";
        String email = "fh@gmail.com";
        String password = "12345";

        assertTrue(user.matchUser(name, email, password));
    }

    @Test(timeout = 5000)
    public void matchUser_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Fletcher Hagen"; //name change
        String email = "fh@gmail.com";
        String password = "12345";

        assertFalse(user.matchUser(name, email, password));
    }

    @Test (timeout = 5000)
    public void matchUser_Fail2(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Fletcher Hagan";
        String email = "fh@yahoo.com"; //email change
        String password = "12345";

        assertFalse(user.matchUser(name, email, password));
    }

    @Test (timeout = 5000)
    public void matchUser_Fail3(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Fletcher Hagan";
        String email = "fh@gmail.com";
        String password = "12365"; //password change

        assertFalse(user.matchUser(name, email, password));
    }

    //update existing user's name
    @Test(timeout = 5000)
    public void updateUser_FullName_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Frank Wong";
        String email = "fw@gmail.com";
        String password = "12345";
        user.createUser(name, email, password);

        name = "Frank Wong2";
        password = "";

        assertTrue(user.updateUser(email, name, password));
    }

    //try to update a user name that does not exist -- fail
    @Test(timeout = 5000)
    public void updateUser_FullName_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Frank Wong";
        String email = "test@gmail.com";
        String password = "";

        assertFalse(user.updateUser(email, name, password));
    }

    //update existing user's password
    @Test(timeout = 5000)
    public void updateUser_Password_Success(){

        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "";
        String email = "zb@gmail.com";
        String password = "123";

        assertTrue(user.updateUser(email, name, password));
    }

    //try to update a user that does not exist -- fail
    @Test(timeout = 5000)
    public void updateUser_Password_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "";
        String email = "test@gmail.com";
        String password = "789";

        assertFalse(user.updateUser(email, name, password));
    }

    //try to update a user pass and full name
    @Test(timeout = 5000)
    public void updateUser_FullNameAndPass_Success(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Niki Khanbeiki";
        String email = "nk@gmail.com";
        String password = "12345";
        user.createUser(name, email, password);

        name = "Niki Khanbeiki2";
        password = "123456789";

        assertTrue(user.updateUser(email, name, password));
    }

    //delete existing user
    @Test(timeout = 5000)
    public void deleteUser_Success1(){

        //create a single user then delete
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);

        String name = "Faisal Yusufzai";
        String email = "fy@gmail.com";
        String password = "12345";
        user.createUser(name, email, password);

        assertTrue(user.deleteUser(email));
    }

    //try to delete a user that does not exist -- fail
    @Test(timeout = 5000)
    public void deleteUser_Fail(){
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager user = new UserDataManager(client);
        String email = "fy@gmail.com";

        assertFalse(user.deleteUser(email));
    }
}
