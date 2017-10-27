package com.unq.integrador.reservation.notifiaction;

import com.unq.integrador.mail.EmailSender;
import com.unq.integrador.reservation.Reservation;

public abstract class Notification {

    private Reservation reservation;

    public Notification(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    abstract protected String getSubject();
    abstract protected String getBody();
    abstract protected String getDestinationAddress();

    public void send() {
        new EmailSender().sendMail(getDestinationAddress(), getSubject(), getBody());
    }
}
