package com.unq.integrador.test.publication;

import com.unq.integrador.publication.Picture;
import com.unq.integrador.publication.Property;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.site.Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class PropertyTest {

    private Property property;
    private PropertyType type;

    @Before
    public void setUp() {
        property = new Property();
        type = mock(PropertyType.class);
    }

    @Test
    public void testType() {
        assertNull(property.getType());
        property.setType(type);
        assertEquals(property.getType(), type);
    }

    @Test
    public void testAddress() {
        String address = "1234 Some street";
        assertNull(property.getAddress());
        property.setAddress(address);
        assertEquals(address, property.getAddress());
    }

    @Test
    public void testCountry() {
        String country = "Argentina";
        assertNull(property.getCountry());
        property.setCountry(country);
        assertEquals(country, property.getCountry());
    }

    @Test
    public void testCity() {
        String city = "Bernal";
        assertNull(property.getCity());
        property.setCity(city);
        assertEquals(city, property.getCity());
    }

    @Test
    public void testCapacity() {
        Integer capacity = 4;
        assertNull(property.getCapacity());
        property.setCapacity(capacity);
        assertEquals(capacity, property.getCapacity());
    }

    @Test
    public void testSurface() {
        Integer surface = 45;
        assertNull(property.getSurface());
        property.setSurface(surface);
        assertEquals(surface, property.getSurface());
    }

    @Test
    public void testAddService() {
        assertEquals(0, property.getServices().size());
        Service svr1 = mock(Service.class);
        Service svr2 = mock(Service.class);
        property.addService(svr1);
        property.addService(svr2);
        assertEquals(2, property.getServices().size());
        assertTrue(property.getServices().contains(svr1));
        assertTrue(property.getServices().contains(svr2));
    }

    @Test
    public void testRemoveService() {
        Service svr1 = mock(Service.class);
        Service svr2 = mock(Service.class);
        property.addService(svr1);
        property.addService(svr2);
        property.removeService(svr1);
        assertFalse(property.getServices().contains(svr1));
        assertTrue(property.getServices().contains(svr2));
    }

    @Test
    public void testAddPicture() {
        assertEquals(0, property.getPictures().size());
        Picture pic1 = mock(Picture.class);
        Picture pic2 = mock(Picture.class);
        property.addPicture(pic1);
        property.addPicture(pic2);
        assertEquals(2, property.getPictures().size());
        assertTrue(property.getPictures().contains(pic1));
        assertTrue(property.getPictures().contains(pic2));
    }

    @Test
    public void testRemovePicture() {
        Picture pic1 = mock(Picture.class);
        Picture pic2 = mock(Picture.class);
        property.addPicture(pic1);
        property.addPicture(pic2);
        assertTrue(property.getPictures().contains(pic1));
        assertTrue(property.getPictures().contains(pic2));
        property.removePicture(pic1);
        assertFalse(property.getPictures().contains(pic1));
        assertTrue(property.getPictures().contains(pic2));
    }


}
