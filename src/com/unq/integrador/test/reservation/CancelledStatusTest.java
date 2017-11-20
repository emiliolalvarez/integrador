package com.unq.integrador.test.reservation;

import com.unq.integrador.reservation.CancelledStatus;
import com.unq.integrador.reservation.FinalizedStatus;
import com.unq.integrador.reservation.Reservation;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CancelledStatusTest {

    private CancelledStatus status;
    private Reservation reservation;

    @Before
    public void setUp() {
        reservation = mock(Reservation.class);
        status = new CancelledStatus(reservation);
    }

    @Test
    public void testAccept() {
        status.accept();
        verifyZeroInteractions(reservation);
    }

    @Test
    public void testReject() {
        status.reject();
        verifyZeroInteractions(reservation);
    }

    @Test
    public void testCancel() {
        status.cancel();
        verifyZeroInteractions(reservation);
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
}
