package com.unq.integrador.test;


import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.user.User;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class PublicationTest {

	private User user, occupant;
	private PropertyType type;
	private HomePagePublisher publisher;
	private PricePeriod pricePeriod;
	private Publication publication;
	private PopUpWindow application;
	private LocalDate startDate, endDate;
	private Reservation reservation;
	
	@Before
	public void setUp() throws Exception {
		user = mock(User.class);
		type = new PropertyType("apartment");
		publisher = mock(HomePagePublisher.class);
		pricePeriod = mock(PricePeriod.class);
		publication = new Publication(user);
		
		occupant = mock(User.class);
		application = mock(PopUpWindow.class);
		startDate = LocalDate.now();
        endDate = startDate.plusDays(30);
        reservation = new Reservation(occupant, publication, startDate, endDate);
	}
	
    @Test
    public void testPriceChange() {
        when(pricePeriod.getPrice()).thenReturn(100f);
        
        publication.addPricePeriod(pricePeriod);
        publication.registerPriceObserver(publisher);
        publication.setType(type);
        publication.modifyPrice(pricePeriod, 50f);
        verify(publisher).publish("No te pierdas esta oferta: Un inmueble "
                + type.getName() +" a tan sólo " + 50.0 +" pesos");
    }

    @Test
    public void testReservationCancelled() {
        publication.setType(type);
        publication.registerReservationCancelledObserver(application);
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.cancel();
        verify(application).popUp("El/la " + type.getName()
                + " que te interesa se ha liberado! Corre a reservarlo!","green", 14);

    }
}