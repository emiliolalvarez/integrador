package com.unq.integrador.test.score.category.value;

import com.unq.integrador.score.category.PropertyScoreCategory;
import com.unq.integrador.score.category.value.PropertyScoreValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PropertyScoreValueTest {

    private PropertyScoreValue propertyScoreValue;
    private Integer scoreCategoryValue;
    private PropertyScoreCategory propertyScoreCategory;

    @Before
    public void setUp() {
        scoreCategoryValue = new Integer(10);
        propertyScoreCategory = mock(PropertyScoreCategory.class);
        propertyScoreValue = new PropertyScoreValue(propertyScoreCategory, scoreCategoryValue);
    }

    @Test
    public void testGetValue() {
        assertEquals(scoreCategoryValue, propertyScoreValue.getValue());
    }

    @Test
    public void testGetCategory() {
        assertEquals(propertyScoreCategory, propertyScoreValue.getCategory());
    }

}
