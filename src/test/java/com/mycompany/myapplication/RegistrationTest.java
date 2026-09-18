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
public class RegistrationTest {
    
    public RegistrationTest() {
    }
    @Test
    public void testCheckUserNameCorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertTrue(login.checkUserName());
    }

    // Test invalid username
    @Test
    public void testCheckUserNameIncorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyle !!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertFalse(login.checkUserName());
    }

    // Test valid password
    @Test
    public void testCheckPasswordCorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertTrue(login.checkPasswordComplexity());
    }

    // Test invalid password
    @Test
    public void testCheckPasswordIncorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertFalse(login.checkPasswordComplexity());
    }

    // Test valid cell phone number
    @Test
    public void testCheckCellPhoneCorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertTrue(login.checkCellPhoneNumber());
    }

    // Test invalid cell phone number
    @Test
    public void testCheckCellPhoneIncorrect() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        Login login = new Login(registration);

        assertFalse(login.checkCellPhoneNumber());
    }

    // Test successful registration
    @Test
    public void testRegisterUserSuccessful() {

        Registration registration = new Registration(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        Login login = new Login(registration);

        assertEquals(
                "User registered successfully.",
                login.registerUser()
        );
    }

    @Test
    public void testSomeMethod() {
    }
    
}
