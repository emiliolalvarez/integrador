package com.unq.integrador.reservation;

public class CanNotRankNonFinalizedReservationException extends Exception {
    public CanNotRankNonFinalizedReservationException(String message) {
        super(message);
    }
}
