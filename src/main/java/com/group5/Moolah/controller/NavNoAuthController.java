package com.group5.Moolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavNoAuthController {

    @GetMapping("/")
    public String homePage(){

        //returns the name of the view to be displayed to the user -- home.html
        return "home";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }

}
