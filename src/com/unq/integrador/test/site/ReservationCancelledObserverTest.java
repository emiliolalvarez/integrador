package com.unq.integrador.test.site;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.NotificationManager;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.site.ReservationCancelledObserver;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservationCancelledObserverTest {

    private PopUpWindow application;
    private ReservationCancelledObserver observer;
    private NotificationManager notificationManager;
    private Publication publication;

    @Before
    public void setUp() {
        application = mock(PopUpWindow.class);
        notificationManager = mock(NotificationManager.class);
        publication = mock(Publication.class);
        observer = new ReservationCancelledObserver(application);
    }

    @Test
    public void testRegisterToNotificationManager() {
        observer.registerTo(notificationManager, publication);
        verify(notificationManager).addReservationCancelledObserver(publication, application);
    }

}
