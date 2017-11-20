package com.unq.integrador.test.reservation;

import com.unq.integrador.mail.ReservationAcceptedBody;
import com.unq.integrador.mail.ReservationBody;
import com.unq.integrador.mail.ReservationRejectedBody;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.*;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class PendingStatusTest {

    private User owner;
    private User occupant;
    private Reservation reservation;
    private Publication publication;
    private PendingStatus status;
    private ReservationBody reservationBodyFactory;
    private String email;
    private ReservationAcceptedBody acceptedBody;
    private ReservationRejectedBody rejectedBody;
    private AcceptedStatus acceptedStatus;
    private RejectedStatus rejectedStatus;
    private CancelledStatus cancelledStatus;
    private String acceptedBodyMessage;
    private String rejectedBodyMessage;

    @Before
    public void setUp() {
        email = "someone@gmail.com";
        acceptedBodyMessage = "Reservation accepted";
        rejectedBodyMessage = "Reservation rejected";
        preparePublicationMock();
        prepareReservationMock();
        prepareReservationBodyFactoryMock();
        status = new PendingStatus(reservation);
    }

    @Test
    public void testAccept() {
        status.setReservationBodyFactory(reservationBodyFactory);
        status.accept();
        verify(reservation).setStatus(acceptedStatus);
        verify(reservation).getPublication();
        verify(owner).getEmail();
        verify(acceptedBody).getMessage(reservation);
        verify(reservation).sendMail(email, "Reservation request accepted", acceptedBodyMessage);
    }

    @Test
    public void testReject() {
        status.setReservationBodyFactory(reservationBodyFactory);
        status.reject();
        verify(reservation).setStatus(rejectedStatus);
        verify(occupant).getEmail();
        verify(rejectedBody).getMessage(reservation);
        verify(reservation).sendMail(email, "Reservation request rejected", rejectedBodyMessage);
    }

    @Test
    public void testCancel() {
        status.cancel();
        verify(reservation).setStatus(cancelledStatus);
        verify(reservation).getPublication();
        verify(publication).notifyCancelledReservation();
    }

    @Test
    public void testPending() {
        status.pending();
        verifyZeroInteractions(reservation);
    }

    @Test
    public void testFinalize() {
        status.finalize();
        verifyZeroInteractions(reservation);
    }

    private void preparePublicationMock() {
        owner = mock(User.class);
        publication = mock(Publication.class);
        when(publication.getOwner()).thenReturn(owner);
        when(owner.getEmail()).thenReturn(email);
    }

    private void prepareReservationMock() {
        occupant = mock(User.class);
        acceptedStatus = mock(AcceptedStatus.class);
        rejectedStatus = mock(RejectedStatus.class);
        cancelledStatus = mock(CancelledStatus.class);
        reservation = mock(Reservation.class);
        when(occupant.getEmail()).thenReturn(email);
        when(reservation.getPublication()).thenReturn(publication);
        when(reservation.getAcceptedStatus()).thenReturn(acceptedStatus);
        when(reservation.getRejectedStatus()).thenReturn(rejectedStatus);
        when(reservation.getCancelledStatus()).thenReturn(cancelledStatus);
        when(reservation.getOccupant()).thenReturn(occupant);
    }

    private void prepareReservationBodyFactoryMock() {
        acceptedBody = mock(ReservationAcceptedBody.class);
        rejectedBody = mock(ReservationRejectedBody.class);
        when(acceptedBody.getMessage(reservation)).thenReturn(acceptedBodyMessage);
        when(rejectedBody.getMessage(reservation)).thenReturn(rejectedBodyMessage);
        reservationBodyFactory = mock(ReservationBody.class);
        when(reservationBodyFactory.getAcceptedBody()).thenReturn(acceptedBody);
        when(reservationBodyFactory.getRejectedBody()).thenReturn(rejectedBody);
    }
}
