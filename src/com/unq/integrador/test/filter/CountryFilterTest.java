package com.unq.integrador.test.filter;

import com.unq.integrador.filter.CountryFilter;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryFilterTest {

    private String country;
    private CountryFilter countryFilter;
    private Publication publication;
    private Property property;

    @Before
    public void setUp() {
        country = "Argentina";
        property = mock(Property.class);
        publication = mock(Publication.class);
        when(publication.getProperty()).thenReturn(property);
        countryFilter = new CountryFilter(country);
    }

    @Test
    public void testFilterIsSatisfied() {
        when(property.getCountry()).thenReturn(country);
        assertTrue(countryFilter.eval(publication));
    }

    @Test
    public void testFilterIsNotSatisfied() {
        when(property.getCountry()).thenReturn("Brasil");
        assertFalse(countryFilter.eval(publication));
    }

}
