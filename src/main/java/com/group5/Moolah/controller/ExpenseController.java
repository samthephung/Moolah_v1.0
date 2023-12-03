package com.group5.Moolah.controller;

import com.group5.Moolah.model.Expense;
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
public class ExpenseController {

    //PostMapping
    @PostMapping("/home")
    public String submitExpenseAdd(@ModelAttribute Expense ex) {
        System.out.println("NAME" + ex.getName());
        return "dashboard";
    }
}
