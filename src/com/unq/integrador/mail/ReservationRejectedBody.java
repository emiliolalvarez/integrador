package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationRejectedBody extends EmailBody {
    @Override
    public String getMessage(Reservation reservation) {
        return reservation.getPublication().getOwner().getName()
                + " " + reservation.getPublication().getOwner().getLastName() + " has rejected your reservation for "
                + reservation.getPublication().getProperty().getType().getName() + " - "
                + reservation.getPublication().getProperty().getCity()
                + ", " + reservation.getPublication().getProperty().getAddress();
    }
}
