package com.unq.integrador.mail;

import com.unq.integrador.reservation.Reservation;

public abstract class MailBody {

    public abstract String getMessage(Reservation reservation);

}
