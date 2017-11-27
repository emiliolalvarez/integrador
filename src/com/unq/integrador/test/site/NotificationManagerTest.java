package com.unq.integrador.test.site;

import com.unq.integrador.publication.PriceLoweredObserver;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.ReservationCancelledObserver;
import com.unq.integrador.site.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class NotificationManagerTest {

    private NotificationManager notificationManager;
    private Publication publication;
    private PriceLoweredObserver priceLoweredObserver;
    private ReservationCancelledObserver reservationCancelledObserver;
    private String propertyTypeName;

    @Before
    public void setUp() {
        notificationManager = new NotificationManager();
        publication = mock(Publication.class);
        priceLoweredObserver = mock(PriceLoweredObserver.class);
        reservationCancelledObserver = mock(ReservationCancelledObserver.class);
        propertyTypeName = "departamento";
    }


    @Test
    public void testRegisterPriceLoweredObserver() {
        notificationManager.register(publication, priceLoweredObserver);
        assertTrue(notificationManager.getPriceLoweredMapper().containsKey(publication));
        assertTrue(notificationManager.getPriceLoweredMapper().get(publication).contains(priceLoweredObserver));
    }

    @Test
    public void testRegisterReservationCancelledObserver() {
        notificationManager.register(publication, reservationCancelledObserver);
        assertTrue(notificationManager.getReservationCancelledMapper().containsKey(publication));
        assertTrue(notificationManager.getReservationCancelledMapper().get(publication).contains(reservationCancelledObserver));
    }

    @Test
    public void testPriceLoweredNotification() {
        Float newPrice = 55f;
        preparePublicationForNotification(publication);
        notificationManager.register(publication, priceLoweredObserver);
        notificationManager.notifyPriceLowered(publication, newPrice);
        verify(priceLoweredObserver).update("No te pierdas esta oferta: Un inmueble " + propertyTypeName +" a tan sólo " + newPrice + " pesos");
    }

    @Test
    public void testReservationCancelledNotification() {
        preparePublicationForNotification(publication);
        notificationManager.register(publication, reservationCancelledObserver);
        notificationManager.notifyReservationCancelled(publication);
        verify(reservationCancelledObserver).update("El/la " + propertyTypeName + " que te interesa se ha liberado! Corre a reservarlo!");
    }

    private void preparePublicationForNotification(Publication publication) {
        PropertyType type = mock(PropertyType.class);
        when(type.getName()).thenReturn(propertyTypeName);
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        when(publication.getProperty()).thenReturn(property);
    }

}
