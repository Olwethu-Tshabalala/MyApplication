/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapplication;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
     @Test
    public void testMessageLengthSuccess() {
        Message message = new Message(
                "1234567890", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "A".repeat(260);

        Message message = new Message(
                "1234567890", 0, "+27718693002", longText);

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageID() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                "1234567890", 0, "0718693002", "Hi tonight");

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertEquals(
                "Message successfully sent.",
                message.SentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage(2));
    }
     @Test
    public void testInvalidMessageID() {
        Message message = new Message(
                "12345", 0, "+27718693002", "Hi tonight");

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testGenerateMessageID() {
        String messageID = Message.generateMessageID();

        assertTrue(messageID.matches("[0-9]{10}"));
    }

    @Test
    public void testInvalidOption() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertEquals(
                "Invalid option. Please choose again.",
                message.SentMessage(4));
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        assertEquals(
                "Message successfully stored.",
                message.storeMessage());
    }
    @Test
    public void testTotalMessagesSent() {
        int previousTotal = Message.getTotalMessagesSent();

        Message message = new Message(
                "1234567890", 0, "+27718693002", "Hi tonight");

        message.SentMessage(1);

        assertEquals(
                previousTotal + 1,
                Message.getTotalMessagesSent());
    }
    @Test
    public void testPrintMessages() {
        Message message = new Message(
                "1234567890", 1,
                "+27718693002", "Hi tonight");

        message.createMessageHash();

        String result = message.printMessages();

        assertTrue(result.contains("Message ID: 1234567890"));
        assertTrue(result.contains("Message Hash: 12:1:HITONIGHT"));
        assertTrue(result.contains("Recipient: +27718693002"));
        assertTrue(result.contains("Message: Hi tonight"));
    }
}
