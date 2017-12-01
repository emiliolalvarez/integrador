package com.unq.integrador.test.reservation;

import com.unq.integrador.mail.EmailSender;
import com.unq.integrador.mail.ReservationAcceptedBody;
import com.unq.integrador.mail.EmailBodyFactory;
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
    private String email;
    private ReservationAcceptedBody acceptedBody;
    private ReservationRejectedBody rejectedBody;
    private AcceptedStatus acceptedStatus;
    private RejectedStatus rejectedStatus;
    private CancelledStatus cancelledStatus;
    private String acceptedBodyMessage;
    private String rejectedBodyMessage;
    private EmailSender emailSender;
    private EmailBodyFactory emailBodyFactory;

    @Before
    public void setUp() {
        email = "someone@gmail.com";
        acceptedBodyMessage = "Reservation accepted";
        rejectedBodyMessage = "Reservation rejected";
        preparePublicationMock();
        prepareReservationMock();
        prepareBodyFactoryMock();
        prepareEmailSenderMock();
        status = new PendingStatus(reservation, emailSender);
    }

    @Test
    public void testAccept() {
        status.accept();
        verify(reservation).setStatus(acceptedStatus);
        verify(reservation).getPublication();
        verify(owner).getEmail();
        verify(emailSender).getBodyFactory();
        verify(emailBodyFactory).getReservationAcceptedBody(reservation);
        verify(acceptedBody).getBody();
        verify(emailSender).sendMail(email, "Reservation request accepted", acceptedBodyMessage);
    }

    @Test
    public void testReject() {
        status.reject();
        verify(reservation).setStatus(rejectedStatus);
        verify(occupant).getEmail();
        verify(emailSender).getBodyFactory();
        verify(emailBodyFactory).getReservationRejectedBody(reservation);
        verify(rejectedBody).getBody();
        verify(emailSender).sendMail(email, "Reservation request rejected", rejectedBodyMessage);
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

    private void prepareBodyFactoryMock() {
        acceptedBody = mock(ReservationAcceptedBody.class);
        rejectedBody = mock(ReservationRejectedBody.class);
        when(acceptedBody.getBody()).thenReturn(acceptedBodyMessage);
        when(rejectedBody.getBody()).thenReturn(rejectedBodyMessage);
        emailBodyFactory = mock(EmailBodyFactory.class);
        when(emailBodyFactory.getReservationAcceptedBody(reservation)).thenReturn(acceptedBody);
        when(emailBodyFactory.getReservationRejectedBody(reservation)).thenReturn(rejectedBody);
    }

    private void prepareEmailSenderMock() {
        emailSender = mock(EmailSender.class);
        when(emailSender.getBodyFactory()).thenReturn(emailBodyFactory);
    }
}
