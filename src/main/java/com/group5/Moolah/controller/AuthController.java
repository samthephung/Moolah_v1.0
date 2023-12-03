package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;

import com.group5.Moolah.services.AccountService;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    //PostMapping handles the form post requests
    //handles post requests sent to /signupSubmitForm and reads form data
    //model attribute will map the data to a User object -- and send it to signup-result.html
    @PostMapping("/signupSubmitForm")
    public String signupFormSubmit(@ModelAttribute User user){

        if (AccountService.signupUser(user)) {
            return "dashboard";
        }
        else {
            return "login";
        }
    }

    @PostMapping("/loginSubmitForm")
    public String loginFormSubmit(@ModelAttribute User user){

        if(AccountService.verifyUser(user)){
            return "dashboard";
        }
        else{
            return "signup";
        }
    }

}
