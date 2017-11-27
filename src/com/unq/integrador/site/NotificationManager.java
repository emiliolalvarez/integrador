package com.unq.integrador.site;

import com.unq.integrador.publication.PriceLoweredObserver;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.ReservationCancelledObserver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NotificationManager {

    private Map<Publication, Set<PriceLoweredObserver>> priceLoweredMapper;
    private Map<Publication, Set<ReservationCancelledObserver>> reservationCancelledMapper;

    public NotificationManager() {
        priceLoweredMapper = new HashMap<>();
        reservationCancelledMapper = new HashMap<>();
    }


    public void register(Publication publication, PriceLoweredObserver observer) {
        if (!priceLoweredMapper.containsKey(publication)) {
            priceLoweredMapper.put(publication, new HashSet<>());
        }
        priceLoweredMapper.get(publication).add(observer);
    }

    public void register(Publication publication, ReservationCancelledObserver observer) {
        if (!reservationCancelledMapper.containsKey(publication)) {
            reservationCancelledMapper.put(publication, new HashSet<>());
        }
        reservationCancelledMapper.get(publication).add(observer);
    }

    public void notifyPriceLowered(Publication publication, Float price) {
        if (priceLoweredMapper.containsKey(publication)) {
            String message = "No te pierdas esta oferta: Un inmueble " + publication.getProperty().getType().getName() +" a tan sólo " + price + " pesos";
            priceLoweredMapper.get(publication).forEach(observer -> observer.update(message));
        }
    }

    public void notifyReservationCancelled(Publication publication) {
        if (reservationCancelledMapper.containsKey(publication)) {
            String message = "El/la " + publication.getProperty().getType().getName() + " que te interesa se ha liberado! Corre a reservarlo!";
            reservationCancelledMapper.get(publication).forEach(observer -> observer.update(message));
        }
    }

    public Map<Publication, Set<PriceLoweredObserver>> getPriceLoweredMapper() {
        return priceLoweredMapper;
    }

    public Map<Publication, Set<ReservationCancelledObserver>> getReservationCancelledMapper() {
        return reservationCancelledMapper;
    }


}
