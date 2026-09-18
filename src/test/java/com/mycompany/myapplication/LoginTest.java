/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.myapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
     @Test
    public void testLoginSuccessful() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertTrue(
                login.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                )
        );
    }

    // Test failed login
    @Test
    public void testLoginFailed() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertFalse(
                login.loginUser(
                        "wrong_user",
                        "wrongPassword"
                )
        );
    }

    // Test successful login message
    @Test
    public void testReturnLoginStatusSuccessful() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                )
        );
    }

    // Test failed login message
    @Test
    public void testReturnLoginStatusFailed() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(
                        "wrong_user",
                        "wrongPassword"
                )
        );
    }

    @Test
    public void testSomeMethod() {
    }
    
}
