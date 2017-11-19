package com.unq.integrador.test;


import com.unq.integrador.publication.PaymentOption;
import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.user.User;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class PublicationTest {

	private User user, occupant;
	private PropertyType type;
	private HomePagePublisher publisher;
	private PricePeriod pricePeriod;
	private Publication publication;
	private PopUpWindow application;
	private LocalDate startDate, endDate;
	private Reservation reservation, dummyReservation;
	private Property property;
	private LocalTime checkIn;
	private LocalTime checkOut;
	
	@Before
	public void setUp() throws Exception {
		user = mock(User.class);
		type = new PropertyType("apartment");
		publisher = mock(HomePagePublisher.class);
		pricePeriod = mock(PricePeriod.class);
		property = getPropertyMock(type);
		publication = new Publication(user, property);

		occupant = mock(User.class);
		application = mock(PopUpWindow.class);
		startDate = LocalDate.now();
		checkIn = LocalTime.parse("11 00 AM", DateTimeFormatter.ofPattern("hh mm a"));
		checkOut = LocalTime.parse("09 00 AM", DateTimeFormatter.ofPattern("hh mm a"));

        endDate = startDate.plusDays(30);
        reservation = new Reservation(occupant, publication, startDate, endDate);
        dummyReservation = mock(Reservation.class);
	}


	@Test
    public void testGetOwner() {
	    assertEquals(user, publication.getOwner());
    }

    @Test
    public void getProperty() {
	    assertEquals(property, publication.getProperty());
    }

    @Test
    public void testCheckInTime() {
	    assertEquals(null, publication.getCheckIn());
	    publication.setCheckIn(checkIn);
	    assertEquals(checkIn, publication.getCheckIn());
    }

    @Test
    public void testCheckOutTime() {
	    assertEquals(null, publication.getCheckOut());
	    publication.setCheckOut(checkOut);
	    assertEquals(checkOut, publication.getCheckOut());
    }

    @Test
    public void getPriceForExistingPeriodDay() {
	    PricePeriod pricePeriod = getPricePeriodMock(10, 1, 20, 2, 550f);
        publication.addPricePeriod(pricePeriod);
        Float price = publication.getDayPrice(LocalDate.parse("2017-01-15"));
        assertEquals(new Float(550), price);
    }

    @Test
    public void getPriceForNonExistingPeriodDay() {
        PricePeriod pricePeriod = getPricePeriodMock(10, 1, 20, 2, 550f);
        publication.addPricePeriod(pricePeriod);
        Float price = publication.getDayPrice(LocalDate.parse("2017-03-15"));
        assertEquals(new Float(0), price);
    }

    @Test
    public void getPriceForDateRange() {
        PricePeriod period1 = getPricePeriodMock(10, 1, 20, 1, 550f);
        PricePeriod period2 = getPricePeriodMock(21, 1, 22, 1, 400f);
        PricePeriod period3 = getPricePeriodMock(23, 1, 21, 2, 300f);
        LocalDate start = LocalDate.parse("2017-01-19");
        LocalDate end = LocalDate.parse("2017-01-23");
        //2 days from period1 and 2days from period2
        publication.addPricePeriod(period1);
        publication.addPricePeriod(period2);
        publication.addPricePeriod(period3);
        assertEquals(new Float(550f+550f+400f+400f+300f), publication.getPrice(start, end));
    }

    @Test
    public void addPricePeriod() {
	    assertEquals(0, publication.getPricePeriods().size());
	    publication.addPricePeriod(pricePeriod);
	    assertEquals(1, publication.getPricePeriods().size());
	    assertTrue(publication.getPricePeriods().contains(pricePeriod));
    }

    @Test
    public void removePricePeriod() {
        publication.addPricePeriod(pricePeriod);
        assertTrue(publication.getPricePeriods().contains(pricePeriod));
        publication.removePricePeriod(pricePeriod);
        assertFalse(publication.getPricePeriods().contains(pricePeriod));
    }

    @Test
    public void testAddPaymentOption() {
	    assertEquals(0, publication.getPaymentOptions().size());
        PaymentOption opt1 = mock(PaymentOption.class);
        publication.addPaymentOption(opt1);
        assertEquals(1, publication.getPaymentOptions().size());
        assertTrue(publication.getPaymentOptions().contains(opt1));
    }

    @Test
    public void testRemovePaymentOption() {
        PaymentOption opt1 = mock(PaymentOption.class);
        PaymentOption opt2 = mock(PaymentOption.class);
        publication.addPaymentOption(opt1);
        publication.addPaymentOption(opt2);
        assertTrue(publication.getPaymentOptions().contains(opt1));
        assertTrue(publication.getPaymentOptions().contains(opt2));
        publication.removePaymentOption(opt1);
        assertFalse(publication.getPaymentOptions().contains(opt1));
        assertTrue(publication.getPaymentOptions().contains(opt2));
    }

    @Test
    public void testPriceChangeNotification() {
        when(pricePeriod.getPrice()).thenReturn(100f);
        publication.addPricePeriod(pricePeriod);
        publication.registerPriceObserver(publisher);
        publication.modifyPrice(pricePeriod, 50f);
        verify(publisher).publish("No te pierdas esta oferta: Un inmueble "
                + type.getName() +" a tan sólo " + 50.0 +" pesos");
    }

    @Test
    public void testReservationCancelledNotification() {
        publication.registerReservationCancelledObserver(application);
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.cancel();
        verify(application).popUp("El/la " + type.getName()
                + " que te interesa se ha liberado! Corre a reservarlo!","green", 14);

    }

    @Test
    public void addReservation() {
        assertFalse(publication.getReservations().contains(dummyReservation));
        when(dummyReservation.getOccupant()).thenReturn(occupant);
        publication.addReservation(dummyReservation);
	    assertTrue(publication.getReservations().contains(dummyReservation));
	    verify(occupant, only()).addReservation(dummyReservation);
    }

    private Property getPropertyMock(PropertyType type) {
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        return property;
    }

    private PricePeriod getPricePeriodMock(Integer fromDay, Integer fromMonth, Integer endDay, Integer endMonth, Float price) {
        PricePeriod pricePeriod = mock(PricePeriod.class);
	    when(pricePeriod.getFromDay()).thenReturn(fromDay);
        when(pricePeriod.getFromMonth()).thenReturn(fromMonth);
        when(pricePeriod.getEndDay()).thenReturn(endDay);
        when(pricePeriod.getEndMonth()).thenReturn(endMonth);
        when(pricePeriod.getPrice()).thenReturn(price);
        return pricePeriod;
    }
}