package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public abstract class ReservationBody implements EmailBody {

    protected Reservation reservation;

    public ReservationBody(Reservation reservation) {
        this.reservation = reservation;
    }
}
