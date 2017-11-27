package com.unq.integrador.publication;

import com.unq.integrador.site.HomePagePublisher;

public class PriceLoweredObserver implements PublicationObserver {

    private HomePagePublisher publisher;

    public PriceLoweredObserver(HomePagePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void update(String message) {
        publisher.publish(message);
    }
}
