package com.unq.integrador.site;

import com.unq.integrador.publication.Publication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NotificationManager {

    private Map<Publication, Set<HomePagePublisher>> priceLoweredMapper;
    private Map<Publication, Set<PopUpWindow>> reservationCancelledMapper;

    public NotificationManager() {
        priceLoweredMapper = new HashMap<>();
        reservationCancelledMapper = new HashMap<>();
    }

    public void register(Publication publication, PublicationObserver observer) {
        observer.registerTo(this, publication);
    }

    public void addPriceLoweredObserver(Publication publication, HomePagePublisher publisher) {
        if (!priceLoweredMapper.containsKey(publication)) {
            priceLoweredMapper.put(publication, new HashSet<>());
        }
        priceLoweredMapper.get(publication).add(publisher);
    }

    public void addReservationCancelledObserver(Publication publication, PopUpWindow application) {
        if (!reservationCancelledMapper.containsKey(publication)) {
            reservationCancelledMapper.put(publication, new HashSet<>());
        }
        reservationCancelledMapper.get(publication).add(application);
    }

    public void notifyPriceLowered(Publication publication, Float price) {
        String message = "No te pierdas esta oferta: Un inmueble " + publication.getProperty().getType().getName() +" a tan sólo " + price + " pesos";
        if (priceLoweredMapper.containsKey(publication)) {
            priceLoweredMapper.get(publication).forEach(publisher -> publisher.publish(message));
        }
    }

    public void notifyReservationCancelled(Publication publication) {
        String message = "El/la " + publication.getProperty().getType().getName() + " que te interesa se ha liberado! Corre a reservarlo!";
        if (reservationCancelledMapper.containsKey(publication)) {
            reservationCancelledMapper.get(publication).forEach(application -> application.popUp(message,"red", 14 ));
        }
    }

    public Map<Publication, Set<HomePagePublisher>> getPriceLoweredMapper() {
        return priceLoweredMapper;
    }

    public Map<Publication, Set<PopUpWindow>> getReservationCancelledMapper() {
        return reservationCancelledMapper;
    }


}
