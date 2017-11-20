package com.unq.integrador.mail;

public class EmailSender implements MailServer {

    private EmailBodyFactory emailBodyFactory;

    public EmailSender() {
        this.emailBodyFactory = new EmailBodyFactory();
    }

    @Override
    public void sendMail(String destinationAddress, String subject, String body) {

    }

    public EmailBodyFactory getBodyFactory() {
        return emailBodyFactory;
    }

}
