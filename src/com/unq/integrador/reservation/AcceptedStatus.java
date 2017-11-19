package com.unq.integrador.reservation;

public class AcceptedStatus extends Status {

    public AcceptedStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {}

    @Override
    public void reject() {}

    @Override
    public void pending() {}

    @Override
    public void finalize() {
        reservation.setStatus(reservation.getFinalizedStatus());
    }

    @Override
    public void cancel() {
        reservation.setStatus(reservation.getCancelledStatus());
        reservation.getPublication().notifyCancelledReservation();
    }
}
