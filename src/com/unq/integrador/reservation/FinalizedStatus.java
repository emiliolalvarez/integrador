package com.unq.integrador.reservation;

public class FinalizedStatus extends Status {

    public FinalizedStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {
        System.out.println("Could not accept an already finalized reservation");
    }

    @Override
    public void reject() {
        System.out.println("Could not reject an already finalized reservation");
    }

    @Override
    public void pending() {
        System.out.println("Could not set as pending an already finalized reservation");
    }

    @Override
    public void finalize() {
        System.out.println("Reservation has already finalized");
    }
}
