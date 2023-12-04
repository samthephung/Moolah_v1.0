package com.group5.Moolah;

import com.group5.Moolah.controller.NavAuthController;
import com.group5.Moolah.model.User;
import com.group5.Moolah.repositories.Constants;
import com.group5.Moolah.repositories.UserDataManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.*;
import static org.junit.Assert.*;

public class NavAuthControllerTest {

    private User testUser = new User("John Doe", "johndoe@example.com", "1234ascd");
    private NavAuthController controller = new NavAuthController();
    /*
    @Test
    public void testDashboardPageFail() {
        assertEquals(controller.dashboardPage(), "home");
    }*/

    @Test
    public void testContactAuthPageFail() {
        assertEquals(controller.contactAuthPage(), "home");
    }

    @Test
    public void testAddExpensePageFail() {
        assertEquals(controller.addExpensePage(), "home");
    }

    @Test
    public void testCalculatePageFail() {
        assertEquals(controller.calculatePage(), "home");
    }

    @Test
    public void testCalculateResultPageFail() {
        assertEquals(controller.calculateResultPage(), "home");
    }

    @Test
    public void testSignUp() {
        assertEquals(controller.signupFormSubmit(testUser), "dashboard");
    }

    @Test
    public void testSignUpAlreadyExistingUser() {
        assertEquals(controller.signupFormSubmit(testUser), "signup");
    }

    @Test
    public void testLoginSuccess() {
        assertEquals(controller.loginFormSubmit(testUser), "dashboard");
    }
    /*
    @Test
    public void testDashboardPageSuccess() {
        assertEquals(controller.dashboardPage(), "dashboard");
    }*/

    @Test
    public void testContactAuthPageSuccess() {
        assertEquals(controller.contactAuthPage(), "contactSignedIn");
    }

    @Test
    public void testAddExpensePageSuccess() {
        assertEquals(controller.addExpensePage(), "addExpense");
    }

    @Test
    public void testCalculatePageSuccess() {
        assertEquals(controller.calculatePage(), "calculate");
    }

    @Test
    public void testCalculateResultPageSuccess() {
        assertEquals(controller.calculateResultPage(), "calculateResult");
    }

    @Test
    public void testLoginFail() {
        User failUser = new User("Jane Doe", "janedoe@example.com", "0987zxyv");
        assertEquals(controller.loginFormSubmit(failUser), "login");
    }

    @AfterClass
    public void cleanup() {
        MongoClient client = MongoClients.create(Constants.URI);
        UserDataManager udm = new UserDataManager(client);
        udm.deleteUser(testUser.getEmailAddress());
    }
}
