package com.unq.integrador.test.publication;

import com.unq.integrador.publication.PaymentOption;
import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.reviewer.PropertyScore;
import com.unq.integrador.score.Score;
import com.unq.integrador.score.category.PropertyScoreCategory;
import com.unq.integrador.score.category.value.PropertyScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.site.*;
import com.unq.integrador.user.User;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
    private PropertyScoreCategory category1;
    private PropertyScoreCategory category2;
    private PropertyScoreCategory category3;
    private NotificationManager notificationManager;
	
	@Before
	public void setUp() throws Exception {
		user = mock(User.class);
		type = new PropertyType("apartment");
		publisher = mock(HomePagePublisher.class);
		pricePeriod = mock(PricePeriod.class);
		property = getPropertyMock(type);
        notificationManager = mock(NotificationManager.class);
        publication = new Publication(user, property);
        publication.setNotificationManager(notificationManager);

		occupant = mock(User.class);
		application = mock(PopUpWindow.class);
		startDate = LocalDate.now();
		checkIn = LocalTime.parse("11 00 AM", DateTimeFormatter.ofPattern("hh mm a"));
		checkOut = LocalTime.parse("09 00 AM", DateTimeFormatter.ofPattern("hh mm a"));

        endDate = startDate.plusDays(30);
        reservation = new Reservation(occupant, publication, startDate, endDate);
        dummyReservation = mock(Reservation.class);

        category1 = getPropertyScoreCategoryMock("Category1");
        category2 = getPropertyScoreCategoryMock("Category2");
        category3 = getPropertyScoreCategoryMock("Category3");
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
	    LocalDate date = LocalDate.parse("2017-01-15");
	    LocalDate[] dates = {date};
	    PricePeriod pricePeriod = getPricePeriodMock(dates, true, 550f);
        publication.addPricePeriod(pricePeriod);
        Float price = publication.getDayPrice(date);
        assertEquals(new Float(550), price);
    }

    @Test
    public void getPriceForNonExistingPeriodDay() {
        LocalDate date = LocalDate.parse("2017-01-15");
        LocalDate[] dates = {date};
        PricePeriod pricePeriod = getPricePeriodMock(dates,false, 550f);
        publication.addPricePeriod(pricePeriod);
        Float price = publication.getDayPrice(LocalDate.parse("2017-03-15"));
        assertEquals(new Float(0), price);
    }

    @Test
    public void testPriceLoweredNotification() {
	    PriceLoweredObserver priceLoweredObserver = mock(PriceLoweredObserver.class);
        when(pricePeriod.getPrice()).thenReturn(100f);
        publication.addPricePeriod(pricePeriod);
        publication.registerObserver(priceLoweredObserver);
        publication.modifyPrice(pricePeriod, 50f);
        verify(notificationManager).register(publication, priceLoweredObserver);
        verify(notificationManager).notifyPriceLowered(publication, 50f);
    }

    @Test
    public void getPriceForDateRange() {
	    LocalDate[] period1Dates = {LocalDate.parse("2017-01-19"), LocalDate.parse("2017-01-20")};
        LocalDate[] period2Dates = {LocalDate.parse("2017-01-21"), LocalDate.parse("2017-01-22")};
        LocalDate[] period3Dates = {LocalDate.parse("2017-01-23")};
        PricePeriod period1 = getPricePeriodMock(period1Dates, true, 550f);
        PricePeriod period2 = getPricePeriodMock(period2Dates, true, 400f);
        PricePeriod period3 = getPricePeriodMock(period3Dates, true, 300f);
        LocalDate start = LocalDate.parse("2017-01-19");
        LocalDate end = LocalDate.parse("2017-01-23");
        //2 days from period1 and 2days from period2 and 1day from period3
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
    public void testReservationCancelledNotification() {
	    ReservationCancelledObserver observer = mock(ReservationCancelledObserver.class);
        publication.registerObserver(observer);
        reservation.setStatus(reservation.getAcceptedStatus());
        reservation.cancel();
        verify(notificationManager).register(publication, observer);
        verify(notificationManager).notifyReservationCancelled(publication);
    }

    @Test
    public void testAddReservation() {
        assertFalse(publication.getReservations().contains(dummyReservation));
        when(dummyReservation.getOccupant()).thenReturn(occupant);
        publication.addReservation(dummyReservation);
	    assertTrue(publication.getReservations().contains(dummyReservation));
	    verify(occupant, only()).addReservation(dummyReservation);
    }

    @Test
    public void testGetPropertyScore() {
        Reservation reservation1 = getReservationWithPropertyScoreMock(new PropertyScoreValue[]{getPropertyScoreValue(category1, 3), getPropertyScoreValue(category2, 4), getPropertyScoreValue(category3, 5)});
        Reservation reservation2 = getReservationWithPropertyScoreMock(new PropertyScoreValue[]{getPropertyScoreValue(category1, 3), getPropertyScoreValue(category2, 2), getPropertyScoreValue(category3, 3)});
        publication.addReservation(reservation1);
        publication.addReservation(reservation2);
        Score score = publication.getPropertyScore();
        Set<ScoreValue> scoreValues = score.getScoreValues();
        assertEquals(3, scoreValues.size());
        Integer scoreCategory0Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category1)).findFirst().get().getValue();
        Integer scoreCategory1Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category2)).findFirst().get().getValue();
        Integer scoreCategory2Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category3)).findFirst().get().getValue();
        assertEquals(scoreCategory0Value, new Integer(3));
        assertEquals(scoreCategory1Value, new Integer(3));
        assertEquals(scoreCategory2Value, new Integer(4));
    }

    private Property getPropertyMock(PropertyType type) {
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        return property;
    }

    private PricePeriod getPricePeriodMock(LocalDate[] dates, Boolean isInPeriod, Float price) {
        PricePeriod pricePeriod = mock(PricePeriod.class);
        when(pricePeriod.isInPeriod(any())).thenReturn(false);
        for (LocalDate date: dates) {
            when(pricePeriod.isInPeriod(date)).thenReturn(isInPeriod);
        }
        when(pricePeriod.getPrice()).thenReturn(price);
        return pricePeriod;
    }

    private Reservation getReservationWithPropertyScoreMock(PropertyScoreValue[] scoreValues) {
	    Reservation reservation = mock(Reservation.class);
	    PropertyScore score = mock(PropertyScore.class);
	    Mockito.doReturn(Arrays.asList(scoreValues).stream().collect(Collectors.toSet())).when(score).getScoreValues();
	    when(reservation.getPropertyScore()).thenReturn(score);
	    when(reservation.getOccupant()).thenReturn(occupant);
	    return reservation;
    }

    private PropertyScoreCategory getPropertyScoreCategoryMock(String name) {
	    PropertyScoreCategory category = mock(PropertyScoreCategory.class);
	    when(category.getName()).thenReturn(name);
	    return category;
    }

    private PropertyScoreValue getPropertyScoreValue(PropertyScoreCategory category, Integer value) {
	    return new PropertyScoreValue(category, value);
    }

}