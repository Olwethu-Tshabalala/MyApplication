/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapplication;

/**
 *
 * @author Student
 */

import java.util.regex.Pattern;
public class Login {
    private Registration registration;

    // Constructor
    public Login(Registration registration) {
        this.registration = registration;
    }

    // Checks username
    public boolean checkUserName() {

        String username = registration.getUsername();

        return username.contains("_") && username.length() <= 5;
    }

    // Checks password complexity
    public boolean checkPasswordComplexity() {

        String password = registration.getPassword();

        String passwordPattern =
                "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        return Pattern.matches(passwordPattern, password);
    }

    // Checks cell phone number
    public boolean checkCellPhoneNumber() {

        String cellPhoneNumber = registration.getCellPhoneNumber();

        String phonePattern = "^\\+27[0-9]{1,10}$";

        return Pattern.matches(phonePattern, cellPhoneNumber);
    }

    // Registration message
    public String registerUser() {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        } else if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        } else if (!checkCellPhoneNumber()) {

            return "Cell phone number incorrectly formatted or does not contain international code.";

        } else {

            return "User registered successfully.";
        }
    }

    // Checks login details
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        return enteredUsername.equals(registration.getUsername())
                && enteredPassword.equals(registration.getPassword());
    }

    // Returns login status
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {

        if (loginUser(enteredUsername, enteredPassword)) {

            return "Welcome " + registration.getFirstName()
                    + ", " + registration.getLastName()
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
    
}
