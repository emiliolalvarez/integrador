package com.unq.integrador.test.publication;

import com.unq.integrador.publication.PriceLoweredObserver;
import com.unq.integrador.site.HomePagePublisher;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PriceLoweredObserverTest {

    private PriceLoweredObserver observer;
    private HomePagePublisher publisher;

    @Before
    public void setUp() {
        publisher = mock(HomePagePublisher.class);
        observer = new PriceLoweredObserver(publisher);
    }

    @Test
    public void testUpdate() {
        String message = "Some message";
        observer.update(message);
        verify(publisher).publish(message);
    }
}
