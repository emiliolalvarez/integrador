package com.unq.integrador.mail;

public class EmailSender implements MailServer {
    @Override
    public void sendMail(String destinationAddress, String subject, String body) {
        System.out.println("Sending email to " + destinationAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
