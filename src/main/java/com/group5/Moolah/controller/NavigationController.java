package com.group5.Moolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    //GetMapping returns views  --  @GetMapping(<some path endpoint>)
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

    @GetMapping("/dashboard")
    public String dashboardPage(Model model){

        //model.addAttribute("firstName", "Sam");
        //model.addAttribute("lastName", "Phung");
        //the model information is then sent to the view dashboard

        //returns the name of the view to be displayed to the user -- dashboard.html
        return "dashboard";
    }

    @GetMapping("/contactAuth")
    public String contactAuthPage(){
        return "contactSignedIn";
    }

    @GetMapping("/addExpense")
    public String addExpensePage(){
        return "addExpense";
    }

    @GetMapping("/calculate")
    public String calculatePage(){
        return "calculate";
    }

    @GetMapping("/calculateResult")
    public String calculateResultPage(){
        return "calculateResult";
    }

}
