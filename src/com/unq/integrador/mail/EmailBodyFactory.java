package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class EmailBodyFactory {

    public EmailBody getReservationAcceptedBody(Reservation reservation) {
        return new ReservationAcceptedBody(reservation);
    }

    public EmailBody getReservationRejectedBody(Reservation reservation) {
        return new ReservationRejectedBody(reservation);
    }
}
