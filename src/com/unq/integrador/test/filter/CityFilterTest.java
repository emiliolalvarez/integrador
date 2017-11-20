package com.unq.integrador.test.filter;

import com.unq.integrador.filter.CityFilter;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityFilterTest {

    private String cityName;
    private CityFilter cityFilter;
    private Publication publication;
    private Property property;

    @Before
    public void setUp() {
       cityName = "Bernal";
       property = mock(Property.class);
       publication = mock(Publication.class);
       when(publication.getProperty()).thenReturn(property);
       cityFilter = new CityFilter(cityName);
    }

    @Test
    public void testFilterIsSatisfied() {
       when(property.getCity()).thenReturn(cityName);
       assertTrue(cityFilter.eval(publication));
    }

    @Test
    public void testFilterIsNotSatisfied() {
        when(property.getCity()).thenReturn("Quilmes");
        assertFalse(cityFilter.eval(publication));
    }

}
