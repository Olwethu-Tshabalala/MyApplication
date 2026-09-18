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

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // Checks cell phone number
    public boolean checkCellPhoneNumber() {

        String cellPhoneNumber = registration.getCellPhoneNumber();

        String phonePattern = "^\\+27[0-9]{9}$";

        return Pattern.matches(phonePattern, cellPhoneNumber);
    }

    // Registration message
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        return "User registered successfully.";
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
                    + " it is great to see you.";
        }

        return "Username or password incorrect, please try again.";
    }
    
}
