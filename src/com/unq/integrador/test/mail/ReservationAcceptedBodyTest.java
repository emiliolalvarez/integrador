package com.unq.integrador.test.mail;

import com.unq.integrador.mail.ReservationAcceptedBody;
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

public class ReservationAcceptedBodyTest {

    private Reservation reservation;
    private ReservationAcceptedBody body;
    private User occupant;
    private String occupantName;
    private String occupantLastName;
    private String propertyTypeName;
    private Publication publication;
    private Property property;
    private PropertyType propertyType;
    private String cityName;
    private String address;

    @Before
    public void setUp() {
        occupantName = "Juan";
        occupantLastName = "Lopez";
        propertyTypeName = "Apartment";
        cityName = "Quilmes";
        address = "Lavalle 353";
        prepareOccupantMock();
        prepareReservationMock();
        body = new ReservationAcceptedBody(reservation);
    }

    @Test
    public void testGetMessage() {
        String message = body.getBody();
        verify(reservation, Mockito.times(2)).getOccupant();
        verify(occupant).getName();
        verify(occupant).getLastName();
        verify(reservation, Mockito.times(3)).getPublication();
        verify(publication, Mockito.times(3)).getProperty();
        verify(property).getType();
        verify(propertyType).getName();
        String expectedMessage = occupantName + " " + occupantLastName + " has requested a reservation for "
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
        reservation = mock(Reservation.class);
        when(reservation.getPublication()).thenReturn(publication);
        when(reservation.getOccupant()).thenReturn(occupant);
    }

    private void prepareOccupantMock() {
        occupant = mock(User.class);
        when(occupant.getName()).thenReturn(occupantName);
        when(occupant.getLastName()).thenReturn(occupantLastName);
    }
}
