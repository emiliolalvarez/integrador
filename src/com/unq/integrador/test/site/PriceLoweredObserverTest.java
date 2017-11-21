package com.unq.integrador.test.site;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PriceLoweredObserverTest {

    private HomePagePublisher publisher;
    private PriceLoweredObserver observer;
    private NotificationManager notificationManager;
    private Publication publication;

    @Before
    public void setUp() {
        publisher = mock(HomePagePublisher.class);
        notificationManager = mock(NotificationManager.class);
        publication = mock(Publication.class);
        observer = new PriceLoweredObserver(publisher);
    }

    @Test
    public void testRegisterToNotificationManager() {
        observer.registerTo(notificationManager, publication);
        verify(notificationManager).addPriceLoweredObserver(publication, publisher);
    }

}
