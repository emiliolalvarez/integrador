package com.unq.integrador.publication;

import com.unq.integrador.site.PopUpWindow;

public class ReservationCancelledObserver implements PublicationObserver {

    private PopUpWindow application;

    public ReservationCancelledObserver(PopUpWindow application) {
        this.application = application;
    }

    @Override
    public void update(String message) {
        application.popUp(message, "red", 14);
    }
}
