package com.unq.integrador.publication;

public interface PublicationSubject {

    void notifyPriceLowered(Float price);
    void notifyCancelledReservation();
    void register(PriceLoweredObserver observer);
    void register(ReservationCancelledObserver observer);
}
