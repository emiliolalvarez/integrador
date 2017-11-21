package com.unq.integrador.test.site;

import com.unq.integrador.site.PropertyType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyTyeTest {

    private String name;
    private PropertyType type;

    @Before
    public void setUp() {
        name = "apartment";
        type = new PropertyType(name);
    }

    @Test
    public void testGetName() {
        assertEquals(name, type.getName());
    }
}
