package com.group5.Moolah;

import com.group5.Moolah.controller.NavAuthController;
import com.group5.Moolah.model.User;
import com.group5.Moolah.repositories.Constants;
import com.group5.Moolah.repositories.UserDataManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class NavAuthControllerTest {
    private NavAuthController controller = new NavAuthController();
    @Nested
    class SuccessTests {
        private User testUser = new User("John Doe", "johndoe@example.com", "1234ascd");

        @BeforeAll
        public static void setup() {
            MongoClient client = MongoClients.create(Constants.URI);
            UserDataManager udm = new UserDataManager(client);
            udm.createUser("John Doe", "johndoe@example.com", "1234ascd");
        }

        @BeforeEach
        public void beforeEachTestSetup() {
            controller.loginFormSubmit(testUser);
        }

        @Test
        public void testSignUpAlreadyExistingUser() {
            Assertions.assertEquals("signup", controller.signupFormSubmit(testUser));
        }

        @Test
        public void testLoginSuccess() {
            Assertions.assertEquals("dashboard", controller.loginFormSubmit(testUser));
        }

        @Test
        public void testContactAuthPageSuccess() {
            Assertions.assertEquals("contactSignedIn", controller.contactAuthPage());
        }

        @Test
        public void testAddExpensePageSuccess() {
            Assertions.assertEquals("addExpense", controller.addExpensePage());
        }

        @Test
        public void testCalculatePageSuccess() {
            Assertions.assertEquals("calculate", controller.calculatePage());
        }

        @Test
        public void testCalculateResultPageSuccess() {
            Assertions.assertEquals("calculateResult", controller.calculateResultPage());
        }

        @AfterAll
        public static void cleanup() {
            MongoClient client = MongoClients.create(Constants.URI);
            UserDataManager udm = new UserDataManager(client);
            udm.deleteUser("johndoe@example.com");
        }
    }

    @Nested
    class FailTests {
        @Test
        public void testContactAuthPageFail() {
            Assertions.assertEquals("home", controller.contactAuthPage());
        }

        @Test
        public void testAddExpensePageFail() {
            Assertions.assertEquals("home", controller.addExpensePage());
        }

        @Test
        public void testCalculatePageFail() {
            Assertions.assertEquals("home", controller.calculatePage());
        }

        @Test
        public void testCalculateResultPageFail() {
            Assertions.assertEquals("home", controller.calculateResultPage());
        }

        @Test
        public void testLoginFail() {
            User failUser = new User("Jane Doe", "janedoe@example.com", "0987zxyv");
            Assertions.assertEquals(controller.loginFormSubmit(failUser), "login");
        }
    }
}
