package com.unq.integrador.publication;

import com.unq.integrador.site.PublicationObserver;

public interface PublicationSubject {

    void notifyPriceLowered(Float price);
    void notifyCancelledReservation();
    void registerObserver(PublicationObserver observer);
}
