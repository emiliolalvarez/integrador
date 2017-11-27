package com.unq.integrador.test.site;

import com.unq.integrador.publication.PriceLoweredObserver;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.ReservationCancelledObserver;
import com.unq.integrador.site.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import static org.mockito.Mockito.*;

public class NotificationManagerTest {
    @Mock(name = "priceLoweredMapper") private Map<Publication, Set<PriceLoweredObserver>> priceLoweredMapper;
    @Mock(name = "reservationCancelledMapper") private Map<Publication, Set<ReservationCancelledObserver>> reservationCancelledMapper;
    @InjectMocks private NotificationManager notificationManager;
    private Publication publication;
    private PriceLoweredObserver priceLoweredObserver;
    private ReservationCancelledObserver reservationCancelledObserver;
    private String propertyTypeName;
    private Set<PriceLoweredObserver> priceLoweredObserverSet;
    private Set<ReservationCancelledObserver> reservationCancelledObserverSet;

    @Before
    public void setUp() {
        notificationManager = new NotificationManager();
        publication = mock(Publication.class);
        priceLoweredObserver = mock(PriceLoweredObserver.class);
        reservationCancelledObserver = mock(ReservationCancelledObserver.class);
        priceLoweredObserverSet = mock(Set.class);
        reservationCancelledObserverSet = mock(Set.class);
        propertyTypeName = "departamento";
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRegisterPriceLoweredObserverWithExistentCollection() {
        preparePriceLoweredMapper(true, publication);
        notificationManager.register(publication, priceLoweredObserver);
        verify(priceLoweredObserverSet).add(priceLoweredObserver);
    }

    @Test
    public void testRegisterReservationCancelledObserverWithExistentCollection() {
        prepareReservationCancelledMapper(true, publication);
        notificationManager.register(publication, reservationCancelledObserver);
        verify(reservationCancelledObserverSet).add(reservationCancelledObserver);
    }

    @Test
    public void testRegisterPriceLoweredObserverWithNonExistentCollection() {
        preparePriceLoweredMapper(false, publication);
        notificationManager.register(publication, priceLoweredObserver);
        verify(priceLoweredMapper).put(publication, eq(Matchers.anySet()));
    }

    @Test
    public void testRegisterReservationCancelledObserverWithNonExistentCollection() {
        prepareReservationCancelledMapper(false, publication);
        notificationManager.register(publication, reservationCancelledObserver);
        verify(reservationCancelledMapper).put(publication, eq(Matchers.anySet()));
    }

    private void preparePriceLoweredMapper(boolean containsKey, Publication publication) {
        when(priceLoweredMapper.containsKey(publication)).thenReturn(containsKey);
        when(priceLoweredMapper.get(publication)).thenReturn(priceLoweredObserverSet);
    }

    @Test
    public void testPriceLoweredNotificationDispatch() {
        Float newPrice = 55f;
        preparePublicationForNotification(publication);
        Set<PriceLoweredObserver> mySet = new HashSet<>();
        mySet.add(priceLoweredObserver);
        when(priceLoweredMapper.get(publication)).thenReturn(mySet);
        when(priceLoweredMapper.containsKey(publication)).thenReturn(true);
        notificationManager.notifyPriceLowered(publication, newPrice);
        verify(priceLoweredObserver).update("No te pierdas esta oferta: Un inmueble " + propertyTypeName +" a tan sólo " + newPrice + " pesos");
    }

    @Test
    public void testReservationCancelledNotificationDispatch() {
        preparePublicationForNotification(publication);
        Set<ReservationCancelledObserver> mySet = new HashSet<>();
        mySet.add(reservationCancelledObserver);
        when(reservationCancelledMapper.get(publication)).thenReturn(mySet);
        when(reservationCancelledMapper.containsKey(publication)).thenReturn(true);
        notificationManager.notifyReservationCancelled(publication);
        verify(reservationCancelledObserver).update("El/la " + propertyTypeName +" que te interesa se ha liberado! Corre a reservarlo!");
    }

    private void preparePublicationForNotification(Publication publication) {
        PropertyType type = mock(PropertyType.class);
        when(type.getName()).thenReturn(propertyTypeName);
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        when(publication.getProperty()).thenReturn(property);
    }

    private void prepareReservationCancelledMapper(boolean containsKey, Publication publication) {
        when(reservationCancelledMapper.containsKey(publication)).thenReturn(containsKey);
        when(reservationCancelledMapper.get(publication)).thenReturn(reservationCancelledObserverSet);
    }

}
