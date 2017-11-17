package com.unq.integrador.test;


import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.user.User;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class PublicationTest {

    @Test
    public void testPriceChange() {
        User user = mock(User.class);
        PropertyType type = new PropertyType("apartment");
        HomePagePublisher publisher = mock(HomePagePublisher.class);
        PricePeriod pricePeriod = mock(PricePeriod.class);
        when(pricePeriod.getPrice()).thenReturn(100f);
        Publication publication = new Publication(user);
        publication.addPricePeriod(pricePeriod);
        publication.registerPriceObserver(publisher);
        publication.setType(type);
        publication.modifyPrice(pricePeriod, 50f);
        verify(publisher).publish("No te pierdas esta oferta: Un inmueble "
                + type.getName() +" a tan sólo " + 50.0 +" pesos");
    }

    @Test
    public void testReservationCancelled() {
        User user = mock(User.class);
        User occupant = mock(User.class);
        PopUpWindow application = mock(PopUpWindow.class);
        PropertyType type = new PropertyType("apartment");
        Publication publication = new Publication(user);
        publication.setType(type);
        publication.registerReservationCancelledObserver(application);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
        Reservation reservation = new Reservation(occupant, publication, startDate, endDate);
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.cancel();
        verify(application).popUp("El/la " + type.getName()
                + " que te interesa se ha liberado! Corre a reservarlo!","green", 14);

    }
}
