package com.group5.Moolah.controller;

import com.group5.Moolah.model.User;
import com.group5.Moolah.model.UserId;
import com.group5.Moolah.services.AccountService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Scope("session")
public class NavAuthController {

    private UserId id = new UserId();

    //PostMapping
    @PostMapping("/signupSubmitForm")
    public String signupFormSubmit(@ModelAttribute User user){

        if (AccountService.signupUser(user)) {
            id.setUserIdentifier(user.getEmailAddress());
            return "dashboard";
        }
        else {
            return "signup";
        }
    }

    @PostMapping("/loginSubmitForm")
    public String loginFormSubmit(@ModelAttribute User user){

        if(AccountService.verifyUser(user)){
            id.setUserIdentifier(user.getEmailAddress());
            System.out.println(id.getUserIdentifier());
            return "dashboard";
        }
        else{
            return "login";
        }
    }

    //GetMapping returns views  --  @GetMapping(<some path endpoint>)
    @GetMapping("/dashboard")
    public String dashboardPage(){ return "dashboard"; }

    @GetMapping("/contactAuth")
    public String contactAuthPage(){

        System.out.println(id.getUserIdentifier());
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
