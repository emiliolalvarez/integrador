package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationRejectedBody extends ReservationBody {

    public ReservationRejectedBody(Reservation reservation) {
        super(reservation);
    }

    @Override
    public String getBody() {
        return reservation.getPublication().getOwner().getName()
                + " " + reservation.getPublication().getOwner().getLastName() + " has rejected your reservation for "
                + reservation.getPublication().getProperty().getType().getName() + " - "
                + reservation.getPublication().getProperty().getCity()
                + ", " + reservation.getPublication().getProperty().getAddress();
    }
}
