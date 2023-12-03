package com.group5.Moolah;

import com.group5.Moolah.repositories.LoginManager;
import org.junit.*;
import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void testBasicHash() {
        String password = "test";
        String expectedResult = "057b8a14ac857a3cf426d4023b2fdb78ca6a7da5ea57584b5a0266683eef36c8";

        assertEquals(LoginManager.passwordHash(password), expectedResult);
    }

    @Test
    public void testComplexHash() {
        String password = "854tn!@q894%^&hgdfASDFHj";
        String expectedResult = "682386fba8e87750b7c85c5f4964cbc02c12949edaffbdc81dd1c1a3f7322173";

        assertEquals(LoginManager.passwordHash(password), expectedResult);

    }
}
