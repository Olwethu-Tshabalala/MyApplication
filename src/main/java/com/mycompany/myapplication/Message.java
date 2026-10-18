/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapplication;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random; 

/**
 *
 * @author Student
 */
public class Message {
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    
    // Generates a random ten-digit message ID
public static String generateMessageID() {

    Random random = new Random();

    long number = 1000000000L
            + (long) (random.nextDouble() * 9000000000L);

    return String.valueOf(number);
}

    // Constructor
    public Message(String messageID, int messageNumber,
            String recipient, String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }
    // Checks that the message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID != null
                && messageID.matches("[0-9]{10}");
    }

    // Checks that the recipient number uses the international format
    public boolean checkRecipientCell() {
        return recipient != null
                && recipient.matches("\\+27[0-9]{9}");
    }
    // Creates the message hash
    public String createMessageHash() {

    String firstTwoDigits = messageID.substring(0, 2);

    String trimmedMessage = message.trim();

    int firstSpace = trimmedMessage.indexOf(' ');
    int lastSpace = trimmedMessage.lastIndexOf(' ');

    String firstWord;
    String lastWord;

    if (firstSpace == -1) {
        firstWord = trimmedMessage;
        lastWord = trimmedMessage;
    } else {
        firstWord = trimmedMessage.substring(0, firstSpace);
        lastWord = trimmedMessage.substring(lastSpace + 1);
    }

    messageHash = (firstTwoDigits + ":"
            + messageNumber + ":"
            + firstWord + lastWord).toUpperCase();

    return messageHash;
    }
    // Checks the length of the message
    public String checkMessageLength() {

        if (message.length() < 250) {
            return "Message ready to send.";
        }

        int extraCharacters = message.length() - 250;

        return "Message exceeds 250 characters by "
                + extraCharacters
                + "; please reduce the size.";
    }
    // Allows the user to choose what to do with the message
    public String SentMessage(int choice) {

        if (choice == 1) {
        return "Message successfully sent.";
    } else if (choice == 2) {
        return "Press 0 to delete the message.";
    } else if (choice == 3) {
        return storeMessage();
    } else {
        return "Invalid option. Please choose again.";
        }
    }
    // Displays the message details
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Returns the total number of messages sent
    public int returnTotalMessagess() {
        return storedMessages.size(); 
    }
    // Stores messages in a JSON file
public String storeMessage() {

    storedMessages.add(this);

    try (FileWriter writer = new FileWriter("messages.json")) {

        writer.write("[\n");

        for (int i = 0; i < storedMessages.size(); i++) {

            Message savedMessage = storedMessages.get(i);

            writer.write("  {\n");
            writer.write("    \"MessageID\": \"" 
                    + savedMessage.messageID + "\",\n");
            writer.write("    \"MessageHash\": \"" 
                    + savedMessage.messageHash + "\",\n");
            writer.write("    \"Recipient\": \"" 
                    + escapeJson(savedMessage.recipient) + "\",\n");
            writer.write("    \"Message\": \"" 
                    + escapeJson(savedMessage.message) + "\"\n");
            writer.write("  }");

            if (i < storedMessages.size() - 1) {
                writer.write(",");
            }

            writer.write("\n");
        }

        writer.write("]\n");

        return "Message successfully stored.";

    } catch (IOException e) {
        storedMessages.remove(this);
        return "Unable to store message.";
    }
}

// Handles special characters in JSON text
private String escapeJson(String text) {
    return text.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
}
    
}
