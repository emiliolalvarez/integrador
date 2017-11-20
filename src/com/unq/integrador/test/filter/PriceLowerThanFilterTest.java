package com.unq.integrador.test.filter;

import com.unq.integrador.filter.PriceLowerThanFilter;
import com.unq.integrador.publication.Publication;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceLowerThanFilterTest {

    private PriceLowerThanFilter filter;
    private Publication publication;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float price;

    @Before
    public void setUp() {
        startDate = LocalDate.now();
        endDate = startDate.plusDays(7);
        publication = mock(Publication.class);
        price = 1500f;
        filter = new PriceLowerThanFilter(price, startDate, endDate);
    }

    @Test
    public void testFilterIsSatisfied() {
        when(publication.getPrice(startDate, endDate)).thenReturn(400f);
        assertTrue(filter.eval(publication));
    }

    @Test
    public void testFilterIsNotSatisfied() {
        when(publication.getPrice(startDate, endDate)).thenReturn(2000f);
        assertFalse(filter.eval(publication));
    }
}
