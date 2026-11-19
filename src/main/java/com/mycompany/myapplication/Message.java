/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapplication;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random; 
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Student
 */
public class Message {
    // Arrays used to store message information
private static Message[] sentMessages = new Message[0];
private static Message[] disregardedMessages = new Message[0];
private static Message[] storedMessages = new Message[0];

private static String[] messageHashes = new String[0];
private static String[] messageIDs = new String[0];
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private static int totalMessagesSent = 0;
    
    // Returns the total number of messages sent
public static int getTotalMessagesSent() {
    return totalMessagesSent;
}
// Returns the number of messages in the Sent Messages array
public static int getSentMessagesCount() {
    return sentMessages.length;
}
public static String getSentMessage(int index) {
    return sentMessages[index].message;
}
public static int getDisregardedMessagesCount() {
    return disregardedMessages.length;
}

public static String getDisregardedMessage(int index) {
    return disregardedMessages[index].message;
}
public static int getStoredMessagesCount() {
    return storedMessages.length;
}

public static String getStoredMessage(int index) {
    return storedMessages[index].message;
}
public static int getMessageIDsCount() {
    return messageIDs.length;
}

public static String getMessageID(int index) {
    return messageIDs[index];
}
public static int getMessageHashesCount() {
    return messageHashes.length;
}

public static String getMessageHash(int index) {
    return messageHashes[index];
}
    
    // Increases the total when a message is sent
public void increaseTotalMessagesSent() {
    totalMessagesSent++;
}
    
    // Generates a random ten-digit message ID
public static String generateMessageID() {

    Random random = new Random();

    long number = (long) (random.nextDouble()
            * 10000000000L);

    return String.format("%010d", number);
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
    public String checkRecipientCell() {
        if (recipient != null
                && recipient.matches("\\+27[0-9]{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    // Creates the message hash without using arrays
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

    firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
    lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

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

        sentMessages = java.util.Arrays.copyOf(
                sentMessages, sentMessages.length + 1);
        sentMessages[sentMessages.length - 1] = this;

        increaseTotalMessagesSent();

    } else if (choice == 2) {

        disregardedMessages = java.util.Arrays.copyOf(
                disregardedMessages, disregardedMessages.length + 1);
        disregardedMessages[disregardedMessages.length - 1] = this;

    } else if (choice == 3) {

        return storeMessage();

    } else {
        return "Invalid option. Please choose again.";
    }

    messageIDs = java.util.Arrays.copyOf(
            messageIDs, messageIDs.length + 1);
    messageIDs[messageIDs.length - 1] = messageID;

    messageHashes = java.util.Arrays.copyOf(
            messageHashes, messageHashes.length + 1);
    messageHashes[messageHashes.length - 1] = messageHash;

    if (choice == 1) {
        return "Message successfully sent.";
    } else {
        return "Press 0 to delete the message.";
    }
}
public String printMessages() {
    return "Message ID: " + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + message;
}

    // Returns the total number of messages sent
public int returnTotalMessagess() {
    return totalMessagesSent; 
    }
    // Stores messages in a JSON file
public String storeMessage() {

    Message[] updatedMessages =
            java.util.Arrays.copyOf(storedMessages, storedMessages.length + 1);

    updatedMessages[storedMessages.length] = this;

    storedMessages = updatedMessages;

    try (FileWriter writer = new FileWriter("messages.json")) {

        writer.write("[\n");

        for (int i = 0; i < storedMessages.length; i++) {

            Message savedMessage = storedMessages[i];

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

            if (i < storedMessages.length - 1) {
                writer.write(",");
            }

            writer.write("\n");
        }

        writer.write("]\n");

        return "Message successfully stored.";

    } catch (IOException e) {

        storedMessages = java.util.Arrays.copyOf(
                storedMessages, storedMessages.length - 1);

        return "Unable to store message.";
    }
    
}

// Handles special characters in JSON text
private static String escapeJson(String text) {
    return text.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
}

// Updates the JSON file after a message is deleted
private static boolean updateStoredMessagesFile() {

    try (FileWriter writer = new FileWriter("messages.json")) {

        writer.write("[\n");

        for (int i = 0; i < storedMessages.length; i++) {

            Message savedMessage = storedMessages[i];

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

            if (i < storedMessages.length - 1) {
                writer.write(",");
            }

            writer.write("\n");
        }

        writer.write("]\n");

        return true;

    } catch (IOException e) {
        return false;
    }
}
// Reads stored messages from the JSON file
public static void loadStoredMessages() {

    storedMessages = new Message[0];
    messageIDs = new String[0];
messageHashes = new String[0];

    try (BufferedReader reader =
            new BufferedReader(new FileReader("messages.json"))) {

        String line;
        String id = "";
        String hash = "";
        String recipient = "";
        String messageText = "";

        while ((line = reader.readLine()) != null) {

            line = line.trim();

            if (line.startsWith("\"MessageID\"")) {
                id = line.split("\"")[3];

            } else if (line.startsWith("\"MessageHash\"")) {
                hash = line.split("\"")[3];

            } else if (line.startsWith("\"Recipient\"")) {
                recipient = line.split("\"")[3];

            } else if (line.startsWith("\"Message\"")) {
                messageText = line.split("\"")[3];

                Message savedMessage =
                        new Message(id, 0, recipient, messageText);
                if (hash == null || hash.equals("null")) {
    hash = savedMessage.createMessageHash();
}

                savedMessage.messageHash = hash;
                // Add the message ID to the Message ID array
messageIDs = java.util.Arrays.copyOf(
        messageIDs, messageIDs.length + 1);

messageIDs[messageIDs.length - 1] = id;

// Add the message hash to the Message Hash array
messageHashes = java.util.Arrays.copyOf(
        messageHashes, messageHashes.length + 1);

messageHashes[messageHashes.length - 1] = hash;

                storedMessages = java.util.Arrays.copyOf(
                        storedMessages, storedMessages.length + 1);

                storedMessages[storedMessages.length - 1] =
                        savedMessage;
            }
        }

    } catch (IOException e) {
        System.out.println("No stored messages found.");
    }
}
// Displays the sender and recipient of stored messages
public static String displaySendersAndRecipients(String sender) {

    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    String result = "";

    for (int i = 0; i < storedMessages.length; i++) {

        result += "Sender: " + sender
                + "\nRecipient: " + storedMessages[i].recipient
                + "\n\n";
    }

    return result;
}
// Finds and displays the longest stored message
public static String displayLongestMessage() {

    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    String longestMessage = storedMessages[0].message;

    for (int i = 1; i < storedMessages.length; i++) {

        if (storedMessages[i].message.length()
                > longestMessage.length()) {

            longestMessage = storedMessages[i].message;
        }
    }

    return longestMessage;
}
// Searches for a stored message using its Message ID
public static String searchMessageByID(String searchID) {

    for (int i = 0; i < storedMessages.length; i++) {

        if (storedMessages[i].messageID.equals(searchID)) {

            return "Recipient: " + storedMessages[i].recipient
                    + "\nMessage: " + storedMessages[i].message;
        }
    }

    return "Message ID not found.";
    }
// Searches for all stored messages sent to a recipient
public static String searchMessagesByRecipient(String searchRecipient) {

    String result = "";

    for (int i = 0; i < storedMessages.length; i++) {

        if (storedMessages[i].recipient.equals(searchRecipient)) {

            result += "Message ID: " + storedMessages[i].messageID
                    + "\nMessage: " + storedMessages[i].message
                    + "\n\n";
        }
    }

    if (result.equals("")) {
        return "No messages found for this recipient.";
    }

    return result;
}
// Deletes a stored message using its Message Hash
public static String deleteMessageByHash(String searchHash) {

    for (int i = 0; i < storedMessages.length; i++) {

        if (storedMessages[i].messageHash.equals(searchHash)) {

            String deletedMessage = storedMessages[i].message;

            Message[] updatedMessages =
                    new Message[storedMessages.length - 1];

            int position = 0;

            for (int j = 0; j < storedMessages.length; j++) {

                if (j != i) {
                    updatedMessages[position] = storedMessages[j];
                    position++;
                }
            }

            storedMessages = updatedMessages;
            if (!updateStoredMessagesFile()) {
    return "Message was removed, but the JSON file could not be updated.";
}

            return "Message: \"" + deletedMessage
                    + "\" successfully deleted.";
        }
    }

    return "Message Hash not found.";
}
// Displays a report of all stored messages
public static String displayMessageReport() {

    if (storedMessages.length == 0) {
        return "No stored messages available.";
    }

    String report = "";

    for (int i = 0; i < storedMessages.length; i++) {

        report += "\nMessage " + (i + 1)
                + "\nMessage ID: " + storedMessages[i].messageID
                + "\nMessage Hash: " + storedMessages[i].messageHash
                + "\nRecipient: " + storedMessages[i].recipient
                + "\nMessage: " + storedMessages[i].message
                + "\n------------------------------\n";
    }

    return report;
}
}