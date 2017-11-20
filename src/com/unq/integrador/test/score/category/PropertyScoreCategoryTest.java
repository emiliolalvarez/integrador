package com.unq.integrador.test.score.category;

import com.unq.integrador.score.category.PropertyScoreCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyScoreCategoryTest {

    private PropertyScoreCategory propertyScoreCategory;
    private String name;

    @Before
    public void setUp() {
        name = "Category 1";
        propertyScoreCategory = new PropertyScoreCategory(name);
    }

    @Test
    public void testGetName() {
        assertEquals(name, propertyScoreCategory.getName());
    }
}
