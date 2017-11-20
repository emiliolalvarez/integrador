package com.unq.integrador.test.site;

import com.unq.integrador.filter.Filter;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.score.category.OccupantScoreCategory;
import com.unq.integrador.score.category.OwnerScoreCategory;
import com.unq.integrador.score.category.PropertyScoreCategory;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.site.Service;
import com.unq.integrador.site.Site;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SiteTest {

    private Site site;
    private Service service;
    private PropertyType type;
    private PropertyScoreCategory propertyScoreCategory;
    private OwnerScoreCategory ownerScoreCategory;
    private OccupantScoreCategory occupantScoreCategory;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        site = new Site();
        service = mock(Service.class);
        type = mock(PropertyType.class);
        propertyScoreCategory = mock(PropertyScoreCategory.class);
        ownerScoreCategory = mock(OwnerScoreCategory.class);
        occupantScoreCategory = mock(OccupantScoreCategory.class);
        user1 = mock(User.class);
        user2 = mock(User.class);
    }

    @Test
    public void testAddPropertyType() {
        assertFalse(site.getPropertyTypes().contains(type));
        site.addPropertyType(type);
        assertTrue(site.getPropertyTypes().contains(type));
    }

    @Test
    public void testRemovePropertyType() {
        site.addPropertyType(type);
        assertTrue(site.getPropertyTypes().contains(type));
        site.removePropertyType(type);
        assertFalse(site.getPropertyTypes().contains(type));
    }

    @Test
    public void testAddService() {
        assertFalse(site.getServices().contains(service));
        site.addService(service);
        assertTrue(site.getServices().contains(service));
    }

    @Test
    public void testRemoveService() {
        site.addService(service);
        assertTrue(site.getServices().contains(service));
        site.removeService(service);
        assertFalse(site.getServices().contains(service));
    }

    @Test
    public void testAddPropertyScoreCategory() {
        assertFalse(site.getPropertyScoreCategories().contains(propertyScoreCategory));
        site.addPropertyScoreCategory(propertyScoreCategory);
        assertTrue(site.getPropertyScoreCategories().contains(propertyScoreCategory));
    }

    @Test
    public void testRemovePropertyScoreCategory() {
        site.addPropertyScoreCategory(propertyScoreCategory);
        assertTrue(site.getPropertyScoreCategories().contains(propertyScoreCategory));
        site.removePropertyScoreCategory(propertyScoreCategory);
        assertFalse(site.getPropertyScoreCategories().contains(propertyScoreCategory));
    }
    
    @Test
    public void testAddOwnerScoreCategory() {
        assertFalse(site.getOwnerScoreCategories().contains(ownerScoreCategory));
        site.addOwnerScoreCategory(ownerScoreCategory);
        assertTrue(site.getOwnerScoreCategories().contains(ownerScoreCategory));
    }

    @Test
    public void testRemoveOwnerScoreCategory() {
        site.addOwnerScoreCategory(ownerScoreCategory);
        assertTrue(site.getOwnerScoreCategories().contains(ownerScoreCategory));
        site.removeOwnerScoreCategory(ownerScoreCategory);
        assertFalse(site.getOwnerScoreCategories().contains(ownerScoreCategory));
    }

    @Test
    public void testAddOccupantScoreCategory() {
        assertFalse(site.getOccupantScoreCategories().contains(occupantScoreCategory));
        site.addOccupantScoreCategory(occupantScoreCategory);
        assertTrue(site.getOccupantScoreCategories().contains(occupantScoreCategory));
    }

    @Test
    public void testRemoveOccupantScoreCategory() {
        site.addOccupantScoreCategory(occupantScoreCategory);
        assertTrue(site.getOccupantScoreCategories().contains(occupantScoreCategory));
        site.removeOccupantScoreCategory(occupantScoreCategory);
        assertFalse(site.getOccupantScoreCategories().contains(occupantScoreCategory));
    }

    @Test
    public void testRegisterUser() {
        assertFalse(site.getUsers().contains(user1));
        site.registerUser(user1);
        assertTrue(site.getUsers().contains(user1));
    }

    @Test
    public void testGetUsers() {
        assertFalse(site.getUsers().contains(user1));
        assertFalse(site.getUsers().contains(user2));
        site.registerUser(user1);
        site.registerUser(user2);
        assertTrue(site.getUsers().contains(user1));
        assertTrue(site.getUsers().contains(user2));
    }

    @Test
    public void getPublications() {
        Publication publication1 = mock(Publication.class);
        Publication publication2 = mock(Publication.class);
        when(user1.getPublications()).thenReturn(Arrays.stream(new Publication[]{publication1}).collect(Collectors.toSet()));
        when(user2.getPublications()).thenReturn(Arrays.stream(new Publication[]{publication2}).collect(Collectors.toSet()));
        site.registerUser(user1);
        site.registerUser(user2);
        Set<Publication> publications = site.getPublications();
        assertTrue(publications.contains(publication1));
        assertTrue(publications.contains(publication2));
        assertEquals(2, publications.size());
    }

    @Test
    public void testSearch() {
        Publication publication1 = mock(Publication.class);
        Publication publication2 = mock(Publication.class);
        when(user1.getPublications()).thenReturn(Arrays.stream(new Publication[]{publication1}).collect(Collectors.toSet()));
        when(user2.getPublications()).thenReturn(Arrays.stream(new Publication[]{publication2}).collect(Collectors.toSet()));
        site.registerUser(user1);
        site.registerUser(user2);

        Filter filter = mock(Filter.class);
        when(filter.eval(publication1)).thenReturn(true);
        when(filter.eval(publication2)).thenReturn(false);

        Set<Publication> publications = site.search(filter);
        assertTrue(publications.contains(publication1));
        assertFalse(publications.contains(publication2));
        assertEquals(1, publications.size());
    }

}
