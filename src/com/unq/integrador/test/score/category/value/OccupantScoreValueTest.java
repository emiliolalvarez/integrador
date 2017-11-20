package com.unq.integrador.test.score.category.value;

import com.unq.integrador.score.category.OccupantScoreCategory;
import com.unq.integrador.score.category.value.OccupantScoreValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class OccupantScoreValueTest {

    private OccupantScoreValue occupantScoreValue;
    private Integer scoreCategoryValue;
    private OccupantScoreCategory occupantScoreCategory;

    @Before
    public void setUp() {
        scoreCategoryValue = new Integer(10);
        occupantScoreCategory = mock(OccupantScoreCategory.class);
        occupantScoreValue = new OccupantScoreValue(occupantScoreCategory, scoreCategoryValue);
    }

    @Test
    public void testGetValue() {
        assertEquals(scoreCategoryValue, occupantScoreValue.getValue());
    }

    @Test
    public void testGetCategory() {
        assertEquals(occupantScoreCategory, occupantScoreValue.getCategory());
    }

}
