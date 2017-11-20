package com.unq.integrador.test.filter;

import com.unq.integrador.filter.HasServiceFilter;
import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HasServiceFilterTest {

    private HasServiceFilter filter;
    private Service service;
    private String serviceName;
    private Publication publication;
    private Set<Service> services;


    @Before
    public void setUp() {
        serviceName = "Breakfast";
        services = new HashSet<>();
        prepareServiceMock();
        preparePublicationMock();
        when(service.getName()).thenReturn(serviceName);
        filter = new HasServiceFilter(service);
    }

    @Test
    public void testFilterIsSatisfied() {
        services.add(service);
        assertTrue(filter.eval(publication));
    }

    @Test
    public void testFilterIsNotSatisfied() {
        assertFalse(filter.eval(publication));
    }

    private void preparePublicationMock() {
        publication = mock(Publication.class);
        Property property = mock(Property.class);

        when(property.getServices()).thenReturn(services);
        when(publication.getProperty()).thenReturn(property);
    }

    private void prepareServiceMock() {
        service = mock(Service.class);
        when(service.getName()).thenReturn(serviceName);
    }

}
