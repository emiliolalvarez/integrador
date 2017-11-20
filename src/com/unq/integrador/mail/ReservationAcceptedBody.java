package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationAcceptedBody extends EmailBody {
    @Override
    public String getMessage(Reservation reservation) {
        return reservation.getOccupant().getName()
                + " " + reservation.getOccupant().getLastName() + " has requested a reservation for "
                + reservation.getPublication().getProperty().getType().getName() + " - " + reservation.getPublication().getProperty().getCity()
                + ", " + reservation.getPublication().getProperty().getAddress();
    }
}
