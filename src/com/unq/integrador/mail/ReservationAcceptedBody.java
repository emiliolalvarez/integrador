package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationAcceptedBody extends ReservationBody {


    public ReservationAcceptedBody(Reservation reservation) {
        super(reservation);
    }

    @Override
    public String getBody() {
        return reservation.getOccupant().getName()
                + " " + reservation.getOccupant().getLastName() + " has requested a reservation for "
                + reservation.getPublication().getProperty().getType().getName() + " - " + reservation.getPublication().getProperty().getCity()
                + ", " + reservation.getPublication().getProperty().getAddress();
    }

}
