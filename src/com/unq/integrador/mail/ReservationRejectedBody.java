package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public class ReservationRejectedBody extends MailBody {
    @Override
    public String getMessage(Reservation reservation) {
        return reservation.getPublication().getOwner().getName()
                + " " + reservation.getPublication().getOwner().getLastName() + " has rejected your reservation for "
                + reservation.getPublication().getType().getName() + " - " + reservation.getPublication().getCity()
                + ", " + reservation.getPublication().getAddress();
    }
}
