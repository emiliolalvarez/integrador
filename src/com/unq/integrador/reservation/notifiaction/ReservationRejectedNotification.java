package com.unq.integrador.reservation.notifiaction;

import com.unq.integrador.reservation.Reservation;

public class ReservationRejectedNotification extends Notification {
    public ReservationRejectedNotification(Reservation reservation) {
        super(reservation);
    }

    @Override
    protected String getSubject() {
        return "Reservation request rejected";
    }

    @Override
    protected String getBody() {
        return getReservation().getPublication().getOwner().getName()
            + " " +  getReservation().getPublication().getOwner().getLastName()
            + " has rejected your reservation request for " + getReservation().getPublication().getCountry() + " "
            + getReservation().getPublication().getCity() + " " + getReservation().getPublication().getAddress();
    }

    @Override
    protected String getDestinationAddress() {
        return getReservation().getOccupant().getEmail();
    }
}
