package com.unq.integrador.test.filter;

import com.unq.integrador.filter.OrFilter;
import com.unq.integrador.filter.Filter;
import com.unq.integrador.publication.Publication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrFilterTest {

    private OrFilter orFilter;
    private Filter leftFilter;
    private Filter rightFilter;
    private Publication publication;

    @Before
    public void setUp() {
        leftFilter = mock(Filter.class);
        rightFilter = mock(Filter.class);
        publication = mock(Publication.class);
        orFilter = new OrFilter(leftFilter, rightFilter);

    }

    @Test
    public void testBothFiltersEvalTrue() {
        when(leftFilter.eval(publication)).thenReturn(true);
        when(rightFilter.eval(publication)).thenReturn(true);
        assertTrue(orFilter.eval(publication));
    }

    @Test
    public void testLeftFilterEvalTrueAndRightFilterEvalFalse() {
        when(leftFilter.eval(publication)).thenReturn(true);
        when(rightFilter.eval(publication)).thenReturn(false);
        assertTrue(orFilter.eval(publication));
    }

    @Test
    public void testLeftFilterEvalFalseAndRightFilterEvalTrue() {
        when(leftFilter.eval(publication)).thenReturn(false);
        when(rightFilter.eval(publication)).thenReturn(true);
        assertTrue(orFilter.eval(publication));
    }

    @Test
    public void testBothFiltersEvalFalse() {
        when(leftFilter.eval(publication)).thenReturn(false);
        when(rightFilter.eval(publication)).thenReturn(false);
        assertFalse(orFilter.eval(publication));
    }
}
