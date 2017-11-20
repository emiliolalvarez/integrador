package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public abstract class EmailBody {

    public abstract String getMessage(Reservation reservation);

}
