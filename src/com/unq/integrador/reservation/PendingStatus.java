package com.unq.integrador.reservation;

import com.unq.integrador.mail.ReservationBody;

public class PendingStatus extends Status {
    private ReservationBody emailBodyFactory;

    public PendingStatus(Reservation reservation) {
        super(reservation);
        emailBodyFactory = new ReservationBody();

    }

    public void setReservationBodyFactory(ReservationBody factory) {
        emailBodyFactory = factory;
    }

    @Override
    public void accept() {
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.sendMail(reservation.getPublication().getOwner().getEmail(), "Reservation request accepted",
                emailBodyFactory.getAcceptedBody().getMessage(reservation));
    }

    @Override
    public void reject() {
        reservation.setStatus(reservation.getRejectedStatus());
        reservation.sendMail(reservation.getOccupant().getEmail(), "Reservation request rejected",
                emailBodyFactory.getRejectedBody().getMessage(reservation));
    }

    @Override
    public void pending() {
        System.out.println("Reservation is already pending");
    }

    @Override
    public void finalize() {
        System.out.println("Could not finalize a non-accepted reservation");
    }

    @Override
    public void cancel() {
        reservation.setStatus(reservation.getCancelledStatus());
        reservation.getPublication().notifyCancelledReservation();
    }
}
