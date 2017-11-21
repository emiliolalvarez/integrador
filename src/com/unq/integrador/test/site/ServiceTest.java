package com.unq.integrador.test.site;

import com.unq.integrador.site.Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {

    private String name;
    private Service service;

    @Before
    public void setUp() {
        name = "apartment";
        service = new Service(name);
    }

    @Test
    public void testGetName() {
        assertEquals(name, service.getName());
    }
}
