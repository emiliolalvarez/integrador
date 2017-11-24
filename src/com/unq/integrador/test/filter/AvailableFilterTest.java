package com.unq.integrador.test.filter;

import com.unq.integrador.filter.AvailableFilter;
import com.unq.integrador.publication.Publication;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvailableFilterTest {

    private AvailableFilter filter;
    private LocalDate startDate, endDate;
    private Publication publication;

    @Before
    public void setUp() {
        startDate = LocalDate.now();
        endDate = startDate.plusDays(20);
        publication = mock(Publication.class);
        filter = new AvailableFilter(startDate, endDate);
    }

    @Test
    public void isAvailable() {
        when(publication.isAvailable(startDate, endDate)).thenReturn(true);
        assertTrue(filter.eval(publication));
    }

    @Test
    public void isNotAvailable() {
        when(publication.isAvailable(startDate, endDate)).thenReturn(false);
        assertFalse(filter.eval(publication));
    }
}
