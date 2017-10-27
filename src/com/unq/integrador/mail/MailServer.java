package com.unq.integrador.mail;

public interface MailServer {
    void sendMail(String destinationAddress, String subject, String body);
}
