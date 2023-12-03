package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import com.group5.Moolah.services.UserAuth;
import com.group5.Moolah.services.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    //PostMapping handles the form post requests
    //handles post requests sent to /signupSubmitForm and reads form data
    //model attribute will map the data to a User object -- and send it to signup-result.html
    @PostMapping("/signupSubmitForm")
    public String signupFormSubmit(@ModelAttribute User user){
        if (UserAuth.userSignup(user.getName(), user.getEmailAddress(), user.getPassword())) {
            return "signup-result";
        }
        else {
            user.setName("User already exists");
            user.setEmailAddress("");
            user.setPassword("");
            return "signup-result";
        }
    }

    @PostMapping("/loginSubmitForm")
    public String loginFormSubmit(@ModelAttribute User user){
        if (UserAuth.userLogin(user.getName(), user.getEmailAddress(), user.getPassword())) {
            return "dashboard";
        }
        else {
            user.setName("User does not exist");
            user.setEmailAddress("");
            user.setPassword("");
            return "signup-result";
        }
    }

}
