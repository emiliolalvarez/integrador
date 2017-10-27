package com.unq.integrador.reservation.notifiaction;

import com.unq.integrador.reservation.Reservation;

public class ReservationRequestNotification extends Notification {


    public ReservationRequestNotification(Reservation reservation) {
        super(reservation);
    }

    @Override
    protected String getSubject() {
        return "Reservation request";
    }

    @Override
    protected String getBody() {
        return getReservation().getOccupant().getName() + " " + getReservation().getOccupant().getLastName()
            + " has requested a reservation for " + getReservation().getPublication().getCountry() + " "
            + getReservation().getPublication().getCity() + " " + getReservation().getPublication().getAddress();
    }

    @Override
    protected String getDestinationAddress() {
        return getReservation().getPublication().getOwner().getEmail();
    }

}
