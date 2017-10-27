package com.unq.integrador.reservation.notifiaction;

import com.unq.integrador.reservation.Reservation;

public class ReservationAcceptedNotification extends Notification {
    public ReservationAcceptedNotification(Reservation reservation) {
        super(reservation);
    }

    @Override
    protected String getSubject() {
        return "Reservation request accepted";
    }

    @Override
    protected String getBody() {
        return getReservation().getPublication().getOwner().getName()
            + " " +  getReservation().getPublication().getOwner().getLastName()
            + " has accepted your reservation request for " + getReservation().getPublication().getCountry() + " "
            + getReservation().getPublication().getCity() + " " + getReservation().getPublication().getAddress();
    }

    @Override
    protected String getDestinationAddress() {
        return getReservation().getOccupant().getEmail();
    }
}
