package com.unq.integrador.test.score.category.value;

import com.unq.integrador.score.category.OwnerScoreCategory;
import com.unq.integrador.score.category.value.OwnerScoreValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class OwnerScoreValueTest {

    private OwnerScoreValue ownerScoreValue;
    private Integer scoreCategoryValue;
    private OwnerScoreCategory ownerScoreCategory;

    @Before
    public void setUp() {
        scoreCategoryValue = new Integer(10);
        ownerScoreCategory = mock(OwnerScoreCategory.class);
        ownerScoreValue = new OwnerScoreValue(ownerScoreCategory, scoreCategoryValue);
    }

    @Test
    public void testGetValue() {
        assertEquals(scoreCategoryValue, ownerScoreValue.getValue());
    }

    @Test
    public void testGetCategory() {
        assertEquals(ownerScoreCategory, ownerScoreValue.getCategory());
    }

}
