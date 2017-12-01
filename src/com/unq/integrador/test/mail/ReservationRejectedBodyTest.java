package com.unq.integrador.test.mail;

import com.unq.integrador.mail.ReservationRejectedBody;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationRejectedBodyTest {

    private Reservation reservation;
    private ReservationRejectedBody body;
    private User owner;
    private String ownerName;
    private String ownerLastName;
    private String propertyTypeName;
    private Publication publication;
    private Property property;
    private PropertyType propertyType;
    private String cityName;
    private String address;

    @Before
    public void setUp() {
        ownerName = "Juan";
        ownerLastName = "Lopez";
        propertyTypeName = "Apartment";
        cityName = "Quilmes";
        address = "Lavalle 353";
        prepareOwnerMock();
        prepareReservationMock();
        body = new ReservationRejectedBody(reservation);
    }

    @Test
    public void testGetMessage() {
        String message = body.getBody();
        verify(owner).getName();
        verify(owner).getLastName();
        verify(publication, Mockito.times(2)).getOwner();
        verify(reservation, Mockito.times(5)).getPublication();
        verify(publication, Mockito.times(3)).getProperty();
        verify(property).getType();
        verify(propertyType).getName();
        String expectedMessage = ownerName + " " + ownerLastName + " has rejected your reservation for "
                + propertyTypeName + " - " + cityName + ", " + address;
        assertEquals(message, expectedMessage);
    }

    private void prepareReservationMock() {
        propertyType = mock(PropertyType.class);
        when(propertyType.getName()).thenReturn(propertyTypeName);
        property = mock(Property.class);
        when(property.getType()).thenReturn(propertyType);
        when(property.getCity()).thenReturn(cityName);
        when(property.getAddress()).thenReturn(address);
        publication = mock(Publication.class);
        when(publication.getProperty()).thenReturn(property);
        when(publication.getOwner()).thenReturn(owner);
        reservation = mock(Reservation.class);
        when(reservation.getPublication()).thenReturn(publication);

    }

    private void prepareOwnerMock() {
        owner = mock(User.class);
        when(owner.getName()).thenReturn(ownerName);
        when(owner.getLastName()).thenReturn(ownerLastName);
    }
}
