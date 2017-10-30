package com.unq.integrador.reservation;

public class AcceptedStatus extends Status {

    public AcceptedStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {
       System.out.println("Reservation was already accepted");
    }

    @Override
    public void reject() {
        System.out.println("Could not reject an already accepted reservation");
    }

    @Override
    public void pending() {
        System.out.println("Could not set as pending an already accepted reservation");
    }

    @Override
    public void finalize() {
        reservation.setStatus(reservation.getFinalizedStatus());
    }

    @Override
    public void cancel() {
        System.out.println("Could not cancel an already accepted reservation");
    }


}
