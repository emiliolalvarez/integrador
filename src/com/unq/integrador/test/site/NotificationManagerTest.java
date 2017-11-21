package com.unq.integrador.test.site;

import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotificationManagerTest {

    private NotificationManager notificationManager;
    private Publication publication;
    private HomePagePublisher publisher;
    private PriceLoweredObserver priceLoweredObserver;
    private ReservationCancelledObserver reservationCancelledObserver;
    private PublicationObserver publicationObserver;
    private PopUpWindow application;
    private String propertyTypeName;

    @Before
    public void setUp() {
        notificationManager = new NotificationManager();
        publication = mock(Publication.class);
        publisher = mock(HomePagePublisher.class);
        priceLoweredObserver = mock(PriceLoweredObserver.class);
        reservationCancelledObserver = mock(ReservationCancelledObserver.class);
        application = mock(PopUpWindow.class);
        propertyTypeName = "departamento";
    }

    @Test
    public void testRegisterPriceLoweredObserver() {
        notificationManager.register(publication, priceLoweredObserver);
        verify(priceLoweredObserver).registerTo(notificationManager, publication);
    }

    @Test
    public void testRegisterReservationCancelledObserver() {
        notificationManager.register(publication, reservationCancelledObserver);
        verify(reservationCancelledObserver).registerTo(notificationManager, publication);
    }

    @Test
    public void testAddPriceLoweredObserver() {
        notificationManager.addPriceLoweredObserver(publication, publisher);
        assertTrue(notificationManager.getPriceLoweredMapper().containsKey(publication));
        assertTrue(notificationManager.getPriceLoweredMapper().get(publication).contains(publisher));
    }

    @Test
    public void testAddReservationCancelledObserver() {
        notificationManager.addReservationCancelledObserver(publication, application);
        assertTrue(notificationManager.getReservationCancelledMapper().containsKey(publication));
        assertTrue(notificationManager.getReservationCancelledMapper().get(publication).contains(application));
    }

    @Test
    public void testPriceLoweredNotification() {
        Float newPrice = 55f;
        preparePublicationForNotification(publication);
        notificationManager.register(publication, new PriceLoweredObserver(publisher));
        notificationManager.notifyPriceLowered(publication, newPrice);
        verify(publisher).publish("No te pierdas esta oferta: Un inmueble " + propertyTypeName +" a tan sólo " + newPrice + " pesos");
    }

    @Test
    public void testReservationCancelledNotification() {
        preparePublicationForNotification(publication);
        notificationManager.register(publication, new ReservationCancelledObserver(application));
        notificationManager.notifyReservationCancelled(publication);
        verify(application).popUp("El/la " + propertyTypeName + " que te interesa se ha liberado! Corre a reservarlo!", "red", 14);
    }

    private void preparePublicationForNotification(Publication publication) {
        PropertyType type = mock(PropertyType.class);
        when(type.getName()).thenReturn(propertyTypeName);
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        when(publication.getProperty()).thenReturn(property);
    }

}
