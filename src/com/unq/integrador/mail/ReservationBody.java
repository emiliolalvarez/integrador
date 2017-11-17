package com.unq.integrador.mail;

public class ReservationBody {

    public MailBody getAcceptedBody() {
        return new ReservationAcceptedBody();
    }

    public MailBody getRejectedBody() {
        return new ReservationRejectedBody();
    }
}
