package com.unq.integrador.site;

import com.unq.integrador.publication.Publication;

public class ReservationCancelledObserver extends PublicationObserver {

    private PopUpWindow application;

    public ReservationCancelledObserver(PopUpWindow application) {
        this.application = application;
    }

    @Override
    public void registerTo(NotificationManager notificationManager, Publication publication) {
        notificationManager.addReservationCancelledObserver(publication, application);
    }
}
