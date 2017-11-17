package com.unq.integrador.reservation;

public class FinalizedStatus extends Status {

    public FinalizedStatus(Reservation reservation) {
        super(reservation);
    }

    @Override
    public void accept() {}

    @Override
    public void reject() {}

    @Override
    public void pending() {}

    @Override
    public void finalize() {}

    @Override
    public void cancel() {}
}
