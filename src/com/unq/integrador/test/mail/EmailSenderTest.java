package com.unq.integrador.test.mail;

import com.unq.integrador.mail.EmailBodyFactory;
import com.unq.integrador.mail.EmailSender;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EmailSenderTest {

    private EmailSender emailSender;
    private EmailBodyFactory emailBodyFactory;

    @Before
    public void setUp() {
        emailBodyFactory = mock(EmailBodyFactory.class);
        emailSender = new EmailSender(emailBodyFactory);
    }

    @Test
    public void testGetEmailBodyFactory() {
        assertEquals(emailBodyFactory, emailSender.getBodyFactory());
    }
}
