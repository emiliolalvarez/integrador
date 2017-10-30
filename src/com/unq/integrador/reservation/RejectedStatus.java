package com.unq.integrador.reservation;

public class RejectedStatus extends Status {

    public RejectedStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {
        System.out.println("Could not accept an already rejected reservation");
    }

    @Override
    public void reject() {
        System.out.println("Reservation was already rejected");
    }

    @Override
    public void pending() {
        System.out.println("Could not set as pending an already rejected reservation");
    }

    @Override
    public void finalize() {
        System.out.println("Could not finalize an already rejected reservation");
    }

    @Override
    public void cancel() {
        System.out.println("Could not cancel an already rejected reservation");
    }
}
