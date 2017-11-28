package com.unq.integrador.test.publication;

import com.unq.integrador.publication.*;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.GlobalScore;
import com.unq.integrador.score.category.PropertyScoreCategory;
import com.unq.integrador.score.category.value.PropertyScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.score.reviewer.PropertyScore;
import com.unq.integrador.site.NotificationManager;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublicationTest {


    private LocalDate startDate, endDate;
    private Reservation reservation;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private PropertyScoreCategory category1;
    private PropertyScoreCategory category2;
    private PropertyScoreCategory category3;
    private PropertyType type;
    private PricePeriod pricePeriod;
    private Property property;
    private User occupant;
    private Reservation reservation1;
    private Reservation reservation2;
    private Reservation reservation3;
    @Mock(name = "owner")
    private User owner;
    @Mock(name = "notificationManager")
    private NotificationManager notificationManager;
    @Mock(name = "reservations")
    private List<Reservation> reservations;
    @InjectMocks
    private Publication publication;

    @Before
    public void setUp() throws Exception {
        pricePeriod = mock(PricePeriod.class);
        property = getPropertyMock(type);
        startDate = LocalDate.now();
        checkIn = LocalTime.parse("11 00 AM", DateTimeFormatter.ofPattern("hh mm a", Locale.US));
        checkOut = LocalTime.parse("09 00 AM", DateTimeFormatter.ofPattern("hh mm a", Locale.US));

        endDate = startDate.plusDays(30);
        reservation = mock(Reservation.class);
        reservation1 = mock(Reservation.class);
        reservation2 = mock(Reservation.class);
        reservation3 = mock(Reservation.class);
        occupant = mock(User.class);

        category1 = getPropertyScoreCategoryMock("Category1");
        category2 = getPropertyScoreCategoryMock("Category2");
        category3 = getPropertyScoreCategoryMock("Category3");
        type = new PropertyType("apartment");
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void testGetOwner() {
        assertEquals(owner, publication.getOwner());
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
        PricePeriod pricePeriod = getPricePeriodMock(dates, false, 550f);
        publication.addPricePeriod(pricePeriod);
        Float price = publication.getDayPrice(LocalDate.parse("2017-03-15"));
        assertEquals(new Float(0), price);
    }

    @Test
    public void testPriceLoweredObserverRegistration() {
        PriceLoweredObserver priceLoweredObserver = mock(PriceLoweredObserver.class);
        publication.register(priceLoweredObserver);
        verify(notificationManager).register(publication, priceLoweredObserver);
    }

    @Test
    public void testPriceLoweredNotification() {
        when(pricePeriod.getPrice()).thenReturn(100f);
        publication.addPricePeriod(pricePeriod);
        publication.modifyPrice(pricePeriod, 50f);
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
        assertEquals(new Float(550f + 550f + 400f + 400f + 300f), publication.getPrice(start, end));
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
    public void testReservationCancelledObserverRegistration() {
        ReservationCancelledObserver observer = mock(ReservationCancelledObserver.class);
        publication.register(observer);
        verify(notificationManager).register(publication, observer);
    }

    @Test
    public void testReservationCancelledNotification() {
        publication.notifyCancelledReservation();
        verify(notificationManager).notifyReservationCancelled(publication);
    }

    @Test
    public void testAddReservation() {
        when(reservation.getOccupant()).thenReturn(occupant);
        publication.addReservation(reservation);
        verify(occupant).addReservation(reservation);
        verify(reservations).add(reservation);
    }

    @Test
    public void testGetPropertyScore() {
        Reservation reservation1 = getReservationWithPropertyScoreMock(new PropertyScoreValue[]{getPropertyScoreValue(category1, 3), getPropertyScoreValue(category2, 4), getPropertyScoreValue(category3, 5)});
        Reservation reservation2 = getReservationWithPropertyScoreMock(new PropertyScoreValue[]{getPropertyScoreValue(category1, 3), getPropertyScoreValue(category2, 2), getPropertyScoreValue(category3, 3)});
        prepareReservations(new Reservation[]{reservation1, reservation2});
        GlobalScore globalScore = publication.getPropertyScore();
        Set<ScoreValue> scoreValues = globalScore.getScoreValues();
        assertEquals(3, scoreValues.size());
        Integer scoreCategory0Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category1)).findFirst().get().getValue();
        Integer scoreCategory1Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category2)).findFirst().get().getValue();
        Integer scoreCategory2Value = scoreValues.stream().filter(scoreValue -> scoreValue.getCategory().equals(category3)).findFirst().get().getValue();
        assertEquals(scoreCategory0Value, new Integer(3));
        assertEquals(scoreCategory1Value, new Integer(3));
        assertEquals(scoreCategory2Value, new Integer(4));
    }

    @Test
    public void testIsAvailableWhenHasNoReservations() {
        assertEquals(0, publication.getReservations().size());
        prepareReservationsWithEmptyIterator();
        assertTrue(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void testIsAvailableWhenNoAcceptedReservationInGivenDateRange() {
        prepareReservationDateRange(endDate.plusDays(1), endDate.plusDays(20));
        addAcceptedReservationToPublication();
        assertTrue(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void testIsNotAvailableIfStartDateIsInAnyReservationDateRange() {
        prepareReservationDateRange(startDate.minusDays(1), startDate.plusDays(5));
        addAcceptedReservationToPublication();
        assertFalse(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void testIsNotAvailableIfEndDateIsInAnyReservationDateRange() {
        prepareReservationDateRange(endDate.minusDays(1), endDate.plusDays(1));
        addAcceptedReservationToPublication();
        assertFalse(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void isNotAvailableIfStartDateEqualsReservationRangeStartDate() {
        prepareReservationDateRange(startDate, startDate.plusDays(5));
        addAcceptedReservationToPublication();
        assertFalse(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void isNotAvailableIfReservationRangeIsIncludedInsideGivenRange() {
        prepareReservationDateRange(startDate.plusDays(1), startDate.minusDays(1));
        addAcceptedReservationToPublication();
        assertFalse(publication.isAvailable(startDate, endDate));
    }

    @Test
    public void getFinalizedReservations() {
        prepareFinalizedReservations();
        List<Reservation> reservations = publication.getFinalizedReservations();
        assertTrue(reservations.contains(reservation1));
        assertFalse(reservations.contains(reservation2));
        assertTrue(reservations.contains(reservation3));
    }

    private void prepareReservations(Reservation[] reservations) {
        when(this.reservations.stream()).thenReturn(Arrays.asList(reservations).stream());
    }

    private void prepareFinalizedReservations() {
        when(reservation1.isFinalized()).thenReturn(true);
        when(reservation2.isFinalized()).thenReturn(false);
        when(reservation3.isFinalized()).thenReturn(true);
        Iterator<Reservation> iterator = getReservationsIterator();
        when(reservations.iterator()).thenReturn(iterator);
        when(this.reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{reservation1, reservation2, reservation3}).stream());
    }

    private void prepareReservationsWithEmptyIterator() {
        Iterator<Reservation> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(false);
        when(reservations.iterator()).thenReturn(iterator);
        when(reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{}).stream());
    }

    private Iterator<Reservation> getReservationsIterator() {
        Iterator<Reservation> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(reservation1).thenReturn(reservation2).thenReturn(reservation3);
        return iterator;
    }

    private void prepareReservationDateRange(LocalDate startDate, LocalDate endDate) {
        when(reservation.getStartDate()).thenReturn(startDate);
        when(reservation.getEndDate()).thenReturn(endDate);
    }

    private void addAcceptedReservationToPublication() {
        when(reservation.getOccupant()).thenReturn(occupant);
        when(reservation.isAccepted()).thenReturn(true);
        when(reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{reservation}).stream());
    }

    private Property getPropertyMock(PropertyType type) {
        Property property = mock(Property.class);
        when(property.getType()).thenReturn(type);
        return property;
    }

    private PricePeriod getPricePeriodMock(LocalDate[] dates, Boolean isInPeriod, Float price) {
        PricePeriod pricePeriod = mock(PricePeriod.class);
        when(pricePeriod.isInPeriod(any())).thenReturn(false);
        for (LocalDate date : dates) {
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