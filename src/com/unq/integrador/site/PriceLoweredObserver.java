package com.unq.integrador.site;

import com.unq.integrador.publication.Publication;

public class PriceLoweredObserver extends PublicationObserver {

    HomePagePublisher publisher;

    public PriceLoweredObserver(HomePagePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void registerTo(NotificationManager notificationManager, Publication publication) {
        notificationManager.addPriceLoweredObserver(publication, publisher);
    }
}
