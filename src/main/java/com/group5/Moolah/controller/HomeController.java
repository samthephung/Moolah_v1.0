package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    //GetMapping returns views
    //PostMapping handles the form post requests

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("firstName", "Sam");
        model.addAttribute("lastName", "Phung");

        //returns the name of the view to be displayed to the user -- home.html
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model){

        //model.addAttribute("firstName", "Sam");
        //model.addAttribute("lastName", "Phung");

        //returns the name of the view to be displayed to the user -- home.html
        return "dashboard";
    }


    @GetMapping("/signup")
    public String signupPage(){

        //returns the name of the view to be displayed to the user -- home.html
        return "signup";
    }

    //handles post requests sent to /users and reads form data
    //modelattribute will map the data to a User object
    @PostMapping("/signupSubmitForm")
    public String signupFormSubmit(@ModelAttribute User user){
        System.out.println(user.getEmailAddress());
        return "signup-result";
    }


    @GetMapping("/login")
    public String loginPage(){

        //returns the name of the view to be displayed to the user -- home.html
        return "login";
    }
}
