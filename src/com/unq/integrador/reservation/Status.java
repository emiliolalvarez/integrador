package com.unq.integrador.reservation;

public abstract class Status {

    protected Reservation reservation;

    public Status(Reservation reservation) {
        this.reservation = reservation;
    }

    public abstract void accept();
    public abstract void reject();
    public abstract void pending();
    public abstract void finalize();

}
