package com.group5.Moolah.controller;

import com.group5.Moolah.model.*;
import com.group5.Moolah.repositories.ExpenseManager;
import com.group5.Moolah.services.AccountService;
import com.group5.Moolah.services.ExpenseService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        System.out.println("NAME: " + expense.getName());
        //call service for adding an expense
        //success message will add up --- or each time you render the dashboard the new expense will come up
        ExpenseService.addExpenseService(id.getUserIdentifier(), expense);
        return "addExpense";
    }

    @PostMapping("/updateExpenseSubmit")
    public String submitExpenseUpdate(@ModelAttribute UpdateExpense updateExpense) {
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        System.out.println("NAME: " + updateExpense.getName());
        //call service for adding an expense
        if(ExpenseService.updateExpenseService(id.getUserIdentifier(), updateExpense)){
            //success message will add up --- or each time you render the dashboard the new expense will come up
            return "addExpense";
        }
        //add a notification that the expense does not exist -- could not update
        return "addExpense";
    }

    @PostMapping("/deleteExpenseSubmit")
    public String submitExpenseDelete(@ModelAttribute DeleteExpense deleteExpense) {
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        System.out.println("NAME: " + deleteExpense.getName());
        //call service for adding an expense
        if(ExpenseService.deleteExpenseService(id.getUserIdentifier(), deleteExpense)){
            //success message will add up --- or each time you render the dashboard the new expense will come up
            return "addExpense";
        }
        //add a notification that the expense does not exist
        return "addExpense";
    }

    @PostMapping("/getExpensesAtDate")
    public String getExpensesAtDate(String date){
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        id.setChosenDate(date);

        return "dashboard";
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

    @PostMapping("/calculateExpensesAtDate")
    public String calculateExpensesAtDate(String date){

        id.setCalculateDate(date);

        return "calculate";
    }

    //GetMapping returns views  --  @GetMapping(<some path endpoint>)
    @GetMapping("/dashboard")
    public String dashboardPage(Model model){
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        model.addAttribute("expenses", ExpenseService.retrieveRecentExpenseService(id.getUserIdentifier()));
        //retrieve specific date items
        //model.addAttribute("daily-expenses",<list of expenses>);
        //System.out.println(id.getChosenDate());

        //not sure if you can have 2 model.addAttributes in one of these functions
        model.addAttribute("dailyexpenses", ExpenseService.retrieveDailyExpenseService(id.getUserIdentifier(), id.getChosenDate()));
        //System.out.println(ExpenseService.retrieveDailyExpenseService(id.getUserIdentifier(), "2020-12-12"));

        return "dashboard";
    }

    @GetMapping("/contactAuth")
    public String contactAuthPage(){

        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }

        System.out.println(id.getUserIdentifier());
        return "contactSignedIn";
    }

    @GetMapping("/addExpense")
    public String addExpensePage(){
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }
        return "addExpense";
    }

    @GetMapping("/calculate")
    public String calculatePage(Model model){
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }
        model.addAttribute("totals", ExpenseService.calculateDailyExpenseService(id.getUserIdentifier(), id.getCalculateDate()));

        return "calculate";
    }

    @GetMapping("/calculateResult")
    public String calculateResultPage(){
        if(id.getUserIdentifier().isEmpty()){
            return "home";
        }
        return "calculateResult";
    }

}
