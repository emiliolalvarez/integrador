package com.unq.integrador.reservation;

import com.unq.integrador.reservation.notifiaction.ReservationAcceptedNotification;
import com.unq.integrador.reservation.notifiaction.ReservationRejectedNotification;
import com.unq.integrador.score.OccupantScore;

public class OwnerReservationService {

    private ReservationService reservationService;

    public OwnerReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void acceptReservation(Reservation reservation) {
        reservation.setStatus(Status.ACCEPTED);
        (new ReservationAcceptedNotification(reservation)).send();
    }

    public void rejectReservation(Reservation reservation) {
        reservation.setStatus(Status.REJECTED);
        (new ReservationRejectedNotification(reservation)).send();
    }

    public void scoreOccupant(Reservation reservation, OccupantScore score) {
        reservation.setOccupantScore(score);
    }

}
