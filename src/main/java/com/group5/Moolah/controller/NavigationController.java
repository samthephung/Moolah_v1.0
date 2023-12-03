package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NavigationController {

    //GetMapping returns views  --  @GetMapping(<some path endpoint>)

    @GetMapping("/")
    public String homePage(){

        //returns the name of the view to be displayed to the user -- home.html
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model){

        //model.addAttribute("firstName", "Sam");
        //model.addAttribute("lastName", "Phung");
        //the model information is then sent to the view dashboard

        //returns the name of the view to be displayed to the user -- dashboard.html
        return "dashboard";
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
