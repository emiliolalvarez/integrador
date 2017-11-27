package com.unq.integrador.test.reservation;

import com.unq.integrador.publication.PaymentOption;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.*;
import com.unq.integrador.score.reviewer.OccupantScore;
import com.unq.integrador.score.reviewer.OwnerScore;
import com.unq.integrador.score.reviewer.PropertyScore;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class ReservationTest {

    private Publication publication;
    private Reservation reservation;
    private Property property;
    private User owner;
    private User occupant;
    private LocalDate startDate, endDate;
    private PendingStatus pendingStatus;
    private AcceptedStatus acceptedStatus;
    private CancelledStatus cancelledStatus;
    private RejectedStatus rejectedStatus;
    private FinalizedStatus finalizedStatus;
    private OwnerScore ownerScore;
    private OccupantScore occupantScore;
    private PropertyScore propertyScore;
    private PaymentOption paymentOption;

    @Before
    public void setUp() {
        occupant = mock(User.class);
        owner = mock(User.class);
        property = mock(Property.class);
        publication = mock(Publication.class);
        startDate = LocalDate.parse("2017-01-10");
        endDate = startDate.plusDays(20);
        reservation = new Reservation(occupant, publication, startDate, endDate);
        pendingStatus = mock(PendingStatus.class);
        acceptedStatus = mock(AcceptedStatus.class);
        cancelledStatus = mock(CancelledStatus.class);
        rejectedStatus = mock(RejectedStatus.class);
        finalizedStatus = mock(FinalizedStatus.class);
        ownerScore = mock(OwnerScore.class);
        occupantScore = mock(OccupantScore.class);
        propertyScore = mock(PropertyScore.class);
        paymentOption = mock(PaymentOption.class);
    }

    @Test
    public void testGetAcceptedStatus() {
        assertThat(reservation.getAcceptedStatus(), instanceOf(AcceptedStatus.class));
    }

    @Test
    public void testGetCancelledStatus() {
        assertThat(reservation.getCancelledStatus(), instanceOf(CancelledStatus.class));
    }

    @Test
    public void testGetRejectedStatus() {
        assertThat(reservation.getRejectedStatus(), instanceOf(RejectedStatus.class));
    }

    @Test
    public void testGetFinalizedStatus() {
        assertThat(reservation.getFinalizedStatus(), instanceOf(FinalizedStatus.class));
    }

    @Test
    public void testGetPendingStatus() {
        assertThat(reservation.getPendingStatus(), instanceOf(PendingStatus.class));
    }

    @Test
    public void testInitialStatus() {
        assertThat(reservation.getStatus(), instanceOf(PendingStatus.class));
    }

    @Test
    public void testStatusChange() {
        assertThat(reservation.getStatus(), instanceOf(PendingStatus.class));
        reservation.setStatus(reservation.getCancelledStatus());
        assertThat(reservation.getStatus(), instanceOf(CancelledStatus.class));
    }

    @Test
    public void testGetStartDate() {
        assertEquals(startDate, reservation.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        assertEquals(endDate, reservation.getEndDate());
    }

    @Test
    public void testSetOwnerScoreOnFinalizedReservation() throws Exception{
        reservation.setStatus(reservation.getFinalizedStatus());
        assertEquals(null, reservation.getOwnerScore());
        reservation.setOwnerScore(ownerScore);
        assertEquals(ownerScore, reservation.getOwnerScore());
    }
    @Test(expected = CanNotRankNonFinalizedReservationException.class)
    public void testSetOwnerScoreOnNonFinalizedReservation() throws Exception{
        reservation.setOwnerScore(ownerScore);
    }

    @Test
    public void testSetOccupantScoreOnFinalizedReservation() throws Exception {
        reservation.setStatus(reservation.getFinalizedStatus());
        assertEquals(null, reservation.getOccupantScore());
        reservation.setOccupantScore(occupantScore);
        assertEquals(occupantScore, reservation.getOccupantScore());
    }

    @Test(expected = CanNotRankNonFinalizedReservationException.class)
    public void testSetOccupantScoreOnNonFinalizedReservation() throws Exception{
        reservation.setOwnerScore(ownerScore);
    }

    @Test
    public void testSetPropertyScoreOnFinalizedReservation() throws Exception {
        reservation.setStatus(reservation.getFinalizedStatus());
        assertEquals(null, reservation.getPropertyScore());
        reservation.setPropertyScore(propertyScore);
        assertEquals(propertyScore, reservation.getPropertyScore());
    }

    @Test(expected = CanNotRankNonFinalizedReservationException.class)
    public void testSetPropertyScoreOnNonFinalizedReservation() throws Exception{
        reservation.setPropertyScore(propertyScore);
    }

    @Test
    public void testAcceptIsCalledOnCurrentStatus() {
        reservation.setStatus(pendingStatus);
        reservation.accept();
        verify(pendingStatus, only()).accept();
    }

    @Test
    public void testCancelIsCalledOnCurrentStatus() {
        reservation.setStatus(pendingStatus);
        reservation.cancel();
        verify(pendingStatus, only()).cancel();
    }

    @Test
    public void testRejectIsCalledOnCurrentStatus() {
        reservation.setStatus(pendingStatus);
        reservation.reject();
        verify(pendingStatus, only()).reject();
    }

    @Test
    public void testPendingIsCalledOnCurrentStatus() {
        reservation.setStatus(acceptedStatus);
        reservation.pending();
        verify(acceptedStatus, only()).pending();
    }

    @Test
    public void testFinalizeIsCalledOnCurrentStatus() {
        reservation.setStatus(pendingStatus);
        reservation.finalize();
        verify(pendingStatus, only()).finalize();
    }

    @Test
    public void testGetPublication() {
        assertEquals(publication, reservation.getPublication());
    }

    @Test
    public void testGetOccupant() {
        assertEquals(occupant, reservation.getOccupant());
    }

    @Test
    public void testIsAccepted() {
        reservation.setStatus(reservation.getAcceptedStatus());
        assertTrue(reservation.isAccepted());
    }

    @Test
    public void testIsFinalized() {
        reservation.setStatus(reservation.getFinalizedStatus());
        assertTrue(reservation.isFinalized());
    }

    @Test
    public void testReservationPaymentOption() {
        assertNull(reservation.getPaymentOption());
        reservation.setPaymentOption(paymentOption);
        assertEquals(paymentOption, reservation.getPaymentOption());
    }
}
