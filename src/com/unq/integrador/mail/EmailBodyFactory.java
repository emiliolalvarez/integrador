package com.unq.integrador.mail;

public class EmailBodyFactory {

    public EmailBody getRservationAcceptedBody() {
        return new ReservationAcceptedBody();
    }

    public EmailBody getReservationRejectedBody() {
        return new ReservationRejectedBody();
    }
}
