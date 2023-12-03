package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NavigationController {

    //GetMapping returns views

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

        //returns the name of the view to be displayed to the user
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
