package com.unq.integrador.test.mail;

import com.unq.integrador.mail.EmailBodyFactory;
import com.unq.integrador.mail.ReservationAcceptedBody;
import com.unq.integrador.mail.ReservationRejectedBody;
import com.unq.integrador.reservation.Reservation;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.mock;

public class EmailBodyFactoryTest {

    private EmailBodyFactory emailBodyFactory;
    private Reservation reservation;


    @Before
    public void setUp() {
        emailBodyFactory = new EmailBodyFactory();
        reservation = mock(Reservation.class);
    }

    @Test
    public void testGetReservationAcceptedBody() {
        assertThat(emailBodyFactory.getReservationAcceptedBody(reservation), instanceOf(ReservationAcceptedBody.class));
    }

    @Test
    public void testGetReservationRejectedBody() {
        assertThat(emailBodyFactory.getReservationRejectedBody(reservation), instanceOf(ReservationRejectedBody.class));
    }
}
