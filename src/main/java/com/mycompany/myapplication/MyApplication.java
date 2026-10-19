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
        String loginStatus = login.returnLoginStatus(
        loginUsername, loginPassword);

System.out.println(loginStatus);

if (login.loginUser(loginUsername, loginPassword)) {

    System.out.println("\nWelcome to QuickChat.");

    int option = 0;

    while (option != 3) {

        System.out.println("\n===== QUICKCHAT MENU =====");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Choose an option: ");

        option = input.nextInt();
        input.nextLine();

        if (option == 1) {

    System.out.print("How many messages would you like to send? ");
    int numberOfMessages = input.nextInt();
    input.nextLine();

    int messagesSent = 0;

    for (int i = 0; i < numberOfMessages; i++) {

        System.out.println("\n===== MESSAGE " + (i + 1) + " =====");

        System.out.print("Enter recipient cell number (+27...): ");
        String recipient = input.nextLine();

        System.out.print("Enter your message: ");
        String messageText = input.nextLine();

        String messageID = Message.generateMessageID();

        Message newMessage = new Message(
                messageID,
                i,
                recipient,
                messageText
        );

        String recipientResult = newMessage.checkRecipientCell();

if (!recipientResult.equals(
        "Cell phone number successfully captured.")) {
    System.out.println(recipientResult);
    i--;
    continue;
        }

        String lengthResult = newMessage.checkMessageLength();

        if (!lengthResult.equals("Message ready to send.")) {
            System.out.println(lengthResult);
            i--;
            continue;
        }

        newMessage.createMessageHash();

        System.out.println("\n" + newMessage.printMessages());

        System.out.println("\nChoose what to do with this message:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.print("Enter your choice: ");

        int messageChoice = input.nextInt();
        input.nextLine();

        System.out.println(newMessage.SentMessage(messageChoice));

if (messageChoice == 1) {
    messagesSent++;

} else if (messageChoice == 2) {

    System.out.print("Enter 0 to delete the message, or another number to keep it: ");
    int deleteChoice = input.nextInt();
    input.nextLine();

    if (deleteChoice == 0) {
        System.out.println("Message deleted.");
    } else {
        System.out.println("Message disregarded.");
    }
}
    } 

    System.out.println("\nTotal messages sent: "
        + Message.getTotalMessagesSent()); 

} else if (option == 2) {
    System.out.println("Coming Soon.");
} else if (option == 3) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid option. Please choose again.");
        }
    }

} else {
    System.out.println("Login failed. QuickChat cannot be opened.");
}

        input.close();
    }
}