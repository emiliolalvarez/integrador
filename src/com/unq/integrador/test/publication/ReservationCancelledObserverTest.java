package com.unq.integrador.test.publication;

import com.unq.integrador.publication.ReservationCancelledObserver;
import com.unq.integrador.site.PopUpWindow;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservationCancelledObserverTest {

    private ReservationCancelledObserver observer;
    private PopUpWindow application;

    @Before
    public void setUp() {
        application = mock(PopUpWindow.class);
        observer = new ReservationCancelledObserver(application);
    }

    @Test
    public void testUpdate() {
        String message = "Some message";
        observer.update(message);
        verify(application).popUp(message, "red", 14);
    }
}
