package com.unq.integrador.reservation;

import com.unq.integrador.mail.EmailSender;

public class PendingStatus extends Status {
    private EmailSender emailSender;

    public PendingStatus(Reservation reservation) {
        super(reservation);
        emailSender = new EmailSender();
    }

    public PendingStatus(Reservation reservation, EmailSender emailSender) {
        super(reservation);
        this.emailSender = emailSender;
    }


    @Override
    public void accept() {
        reservation.setStatus(reservation.getAcceptedStatus());
        emailSender.sendMail(reservation.getPublication().getOwner().getEmail(), "Reservation request accepted",
                emailSender.getBodyFactory().getRservationAcceptedBody().getMessage(reservation));
    }

    @Override
    public void reject() {
        reservation.setStatus(reservation.getRejectedStatus());
        emailSender.sendMail(reservation.getOccupant().getEmail(), "Reservation request rejected",
                emailSender.getBodyFactory().getReservationRejectedBody().getMessage(reservation));
    }

    @Override
    public void pending() {}

    @Override
    public void finalize() {}

    @Override
    public void cancel() {
        reservation.setStatus(reservation.getCancelledStatus());
        reservation.getPublication().notifyCancelledReservation();
    }
}
