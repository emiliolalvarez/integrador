package com.unq.integrador.reservation;

class CancelledStatus extends Status {

    public CancelledStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {
        System.out.println("Could not accept an already cancelled reservation");
    }

    @Override
    public void reject() {
        System.out.println("Reservation is already cancelled");
    }

    @Override
    public void pending() {
        System.out.println("Could not set as pending an already cancelled reservation");
    }

    @Override
    public void finalize() {
        System.out.println("Could not finalize an already cancelled reservation");
    }

    @Override
    public void cancel() {
        System.out.println("Reservation was already cancelled");
    }
}
