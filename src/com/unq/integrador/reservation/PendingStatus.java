package com.unq.integrador.reservation;

public class PendingStatus extends Status {
    public PendingStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.sendMail(reservation.getPublication().getOwner().getEmail(), "Reservation request",
                reservation.getOccupant().getName()
                        + " " + reservation.getOccupant().getLastName() + " has requested a reservation for "
                        + reservation.getPublication().getType().getName() + " - " + reservation.getPublication().getCity()
                        + ", " + reservation.getPublication().getAddress());
    }

    @Override
    public void reject() {
        reservation.setStatus(reservation.getRejectedStatus());
        reservation.sendMail(reservation.getOccupant().getEmail(), "Reservation request rejected",
                reservation.getPublication().getOwner().getName()
                        + " " + reservation.getPublication().getOwner().getLastName() + " has rejected your reservation for "
                        + reservation.getPublication().getType().getName() + " - " + reservation.getPublication().getCity()
                        + ", " + reservation.getPublication().getAddress());
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
    }
}
