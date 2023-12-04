package com.group5.Moolah.controller;

import com.group5.Moolah.model.DeleteExpense;
import com.group5.Moolah.model.Expense;
import com.group5.Moolah.model.User;
import com.group5.Moolah.model.UserId;
import com.group5.Moolah.services.AccountService;
import com.group5.Moolah.services.ExpenseService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Scope("session")
public class NavAuthController {

    //how to prevent an unauthorized user from accessing different routes?
    //if the string is uninitialized with "" then no ones is logged in
    private UserId id = new UserId("");

    //PostMapping for Expenses start here!
    @PostMapping("/addExpenseSubmit")
    public String submitExpenseAdd(@ModelAttribute Expense expense) {

        System.out.println("NAME: " + expense.getName());
        //call service for adding an expense
        if(ExpenseService.addExpenseService(id.getUserIdentifier(), expense)){
            //success message will add up --- or each time you render the dashboard the new expense will come up
            return "dashboard";
        }
        return "dashboard";
    }

    @PostMapping("/deleteExpenseSubmit")
    public String submitExpenseDelete(@ModelAttribute DeleteExpense deleteExpense) {

        System.out.println("NAME: " + deleteExpense.getName());
        //call service for adding an expense
        if(ExpenseService.deleteExpenseService(id.getUserIdentifier(), deleteExpense)){
            //success message will add up --- or each time you render the dashboard the new expense will come up
            return "dashboard";
        }
        //add a notification that the expense does not exist
        return "addExpense";
    }

    //PostMapping -- logging in
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

    @PostMapping("/dashboard")
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
    public String dashboardPage(){
        if(id.getUserIdentifier().equals("")){
            return "home";
        }
        return "dashboard";
    }

    @GetMapping("/contactAuth")
    public String contactAuthPage(){

        if(id.getUserIdentifier().equals("")){
            return "home";
        }

        System.out.println(id.getUserIdentifier());
        return "contactSignedIn";
    }

    @GetMapping("/addExpense")
    public String addExpensePage(){
        if(id.getUserIdentifier().equals("")){
            return "home";
        }
        return "addExpense";
    }

    @GetMapping("/calculate")
    public String calculatePage(){
        if(id.getUserIdentifier().equals("")){
            return "home";
        }
        return "calculate";
    }

    @GetMapping("/calculateResult")
    public String calculateResultPage(){
        if(id.getUserIdentifier().equals("")){
            return "home";
        }
        return "calculateResult";
    }

}
