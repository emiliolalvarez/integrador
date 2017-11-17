package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationAcceptedBody extends MailBody {
    @Override
    public String getMessage(Reservation reservation) {
        return reservation.getOccupant().getName()
                + " " + reservation.getOccupant().getLastName() + " has requested a reservation for "
                + reservation.getPublication().getType().getName() + " - " + reservation.getPublication().getCity()
                + ", " + reservation.getPublication().getAddress();
    }
}
