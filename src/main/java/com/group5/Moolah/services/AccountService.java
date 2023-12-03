package com.group5.Moolah.services;

import com.group5.Moolah.model.User;
import com.group5.Moolah.repositories.UserAuthManager;

public class AccountService {

    //acts as an abtraction between the controller and repository classes -- since the controller should only speak to the service layer (which are these classes)
    //these classes will call the repository methods that perform the create, update, retrieve and delete operations
    public static Boolean verifyUser(User user){
        return UserAuthManager.userLogin(user.getName(), user.getEmailAddress(), user.getPassword());
    }

    public static Boolean signupUser(User user){
        return UserAuthManager.userSignup(user.getName(), user.getEmailAddress(), user.getPassword());
    }

}
