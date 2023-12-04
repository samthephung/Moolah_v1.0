package com.group5.Moolah;

import com.group5.Moolah.controller.NavAuthController;
import com.group5.Moolah.model.DeleteExpense;
import com.group5.Moolah.model.Expense;
import com.group5.Moolah.model.UpdateExpense;
import com.group5.Moolah.model.User;
import com.group5.Moolah.repositories.Constants;
import com.group5.Moolah.repositories.UserDataManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.*;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class NavAuthControllerTest {
    private NavAuthController controller = new NavAuthController();
    @Nested
    class SuccessTests {
        private User testUser = new User("John Doe", "johndoe@example.com", "1234ascd");
        private Expense testExpense = new Expense("test", 420.69, "69/69/6969", "card", "no", "test");
        private UpdateExpense updateExpense = new UpdateExpense(
                "test",
                420.69,
                "69/69/6969",
                "card",
                "no",
                "test",
                "testUpdate",
                42069.69,
                "96/96/9696",
                "check",
                "yes",
                "testUpdate"
                );

        private DeleteExpense deleteExpense = new DeleteExpense("test", 420.69);

        private Model testModel = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };

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

        @Test
        public void testSubmitExpenseAdd() {
            Assertions.assertEquals("addExpense", controller.submitExpenseAdd(testExpense));
        }

        @Test
        public void testSubmitExpenseUpdate() {
            controller.submitExpenseAdd(testExpense);
            Assertions.assertEquals("addExpense", controller.submitExpenseUpdate(updateExpense));
        }

        @Test
        public void testSubmitExpenseUpdateDoesntExist() {
            controller.submitExpenseDelete(deleteExpense);
            Assertions.assertEquals("addExpense", controller.submitExpenseUpdate(updateExpense));
        }

        @Test
        public void testSubmitExpenseDelete() {
            controller.submitExpenseAdd(testExpense);
            Assertions.assertEquals("addExpense", controller.submitExpenseDelete(deleteExpense));
        }

        @Test
        public void testSubmitExpenseDeleteDoesntExist() {
            Assertions.assertEquals("addExpense", controller.submitExpenseDelete(deleteExpense));
        }

        @Test
        public void testGetExpensesAtDateSuccess() {
            Assertions.assertEquals("dashboard", controller.getExpensesAtDate("69/69/6969"));
        }

        @Test
        public void testDashboardPage() {
            Assertions.assertEquals("dashboard", controller.dashboardPage(testModel));
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
        private Expense testExpense = new Expense("test", 420.69, "69/69/6969", "card", "no", "test");
        private UpdateExpense updateExpense = new UpdateExpense(
                "test",
                420.69,
                "69/69/6969",
                "card",
                "no",
                "test",
                "testUpdate",
                42069.69,
                "96/96/9696",
                "check",
                "yes",
                "testUpdate"
        );

        private Model testModel = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };

        private DeleteExpense deleteExpense = new DeleteExpense("test", 420.69);

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

        @Test
        public void testGetExpensesAtDateFail() {
            Assertions.assertEquals("home", controller.getExpensesAtDate("69/69/6969"));
        }

        @Test
        public void testSubmitExpenseAddFail() {
            Assertions.assertEquals("home", controller.submitExpenseAdd(testExpense));
        }

        @Test
        public void testSubmitExpenseUpdateFail() {
            Assertions.assertEquals("home", controller.submitExpenseUpdate(updateExpense));
        }

        @Test
        public void testSubmitExpenseDeleteFail() {
            Assertions.assertEquals("home", controller.submitExpenseDelete(deleteExpense));
        }

        @Test
        public void testDashboardPageFail() {
            Assertions.assertEquals("home", controller.dashboardPage(testModel));
        }
    }

    @Nested
    class TestSignup {
        private User testUser = new User("John Doe", "johndoe@example.com", "1234ascd");
        @Test
        public void testSignupSuccess() {
            Assertions.assertEquals(controller.signupFormSubmit(testUser), "dashboard");
        }

        @AfterAll
        public static void cleanup() {
            MongoClient client = MongoClients.create(Constants.URI);
            UserDataManager udm = new UserDataManager(client);
            udm.deleteUser("johndoe@example.com");
        }
    }
}
