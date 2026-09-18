/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.myapplication;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class MyApplication {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== REGISTRATION =====");

        // Get first name
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        // Get last name
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        // Get username
        System.out.print("Enter your username: ");
        String username = input.nextLine();

        // Get password
        System.out.print("Enter your password: ");
        String password = input.nextLine();

        // Get cell phone number
        System.out.print("Enter your South African cell phone number: ");
        String cellPhoneNumber = input.nextLine();

        // Registration captures the user's details
        Registration registration = new Registration(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        // Login verifies the user's details
        Login login = new Login(registration);

        // Display registration result
        System.out.println();
        System.out.println("===== REGISTRATION RESULT =====");
        System.out.println(login.registerUser());

        // Login section
        System.out.println();
        System.out.println("===== LOGIN =====");

        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();

        // Display login result
        System.out.println();
        System.out.println("===== LOGIN RESULT =====");
        System.out.println(
                login.returnLoginStatus(loginUsername, loginPassword)
        );

        input.close();
    }
}