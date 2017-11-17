package com.unq.integrador.publication;

import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;

public interface PublicationSubject {

    public void notifyPriceChange(Float price);
    public void notifyCancelledReservation();
    public void registerPriceObserver(HomePagePublisher publisher);
    public void registerReservationCancelledObserver(PopUpWindow application);
}
