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

public class AcceptedStatusTest {

    private Reservation reservation;
    private Publication publication;
    private AcceptedStatus status;
    private CancelledStatus cancelledStatus;
    private FinalizedStatus finalizedStatus;

    @Before
    public void setUp() {
        cancelledStatus = mock(CancelledStatus.class);
        finalizedStatus =  mock(FinalizedStatus.class);
        publication = mock(Publication.class);
        reservation = prepareReservationMock();
        status = new AcceptedStatus(reservation);
    }


    @Test
    public void testCancel() {
        status.cancel();
        verify(reservation).setStatus(cancelledStatus);
        verify(reservation).getPublication();
        verify(publication, only()).notifyCancelledReservation();
    }

    @Test
    public void testFinalize() {
        status.finalize();
        verify(reservation).setStatus(finalizedStatus);

    }

    private Reservation prepareReservationMock() {
        reservation = mock(Reservation.class);
        when(reservation.getPublication()).thenReturn(publication);
        when(reservation.getCancelledStatus()).thenReturn(cancelledStatus);
        when(reservation.getFinalizedStatus()).thenReturn(finalizedStatus);
        return reservation;
    }
}
