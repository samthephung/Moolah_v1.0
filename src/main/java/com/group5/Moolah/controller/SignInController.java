package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class SignInController {

    //PostMapping handles the form post requests

    //handles post requests sent to /signupSubmitForm and reads form data
    //model attribute will map the data to a User object -- and send it to signup-result.html
    @PostMapping("/signupSubmitForm")
    public String signupFormSubmit(@ModelAttribute User user){
        //System.out.println(user.getEmailAddress());
        return "signup-result";
    }

    @PostMapping("/loginSubmitForm")
    public String loginFormSubmit(@ModelAttribute User user){
        user.setName(" ");
        return "signup-result";
    }

}
