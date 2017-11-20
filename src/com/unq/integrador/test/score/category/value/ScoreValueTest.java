package com.unq.integrador.test.score.category.value;

import com.unq.integrador.score.category.ScoreCategory;
import com.unq.integrador.score.category.value.ScoreValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ScoreValueTest {

    private ScoreValue scoreValue;
    private ScoreCategory scoreCategory;
    private Integer scoreCategoryValue;

    @Before
    public void setUp() {
        class MyScoreValue extends ScoreValue {
            public MyScoreValue(ScoreCategory category, Integer value) {
                super(category, value);
            }
        }
        scoreCategory = mock(ScoreCategory.class);
        scoreCategoryValue = new Integer(3);
        scoreValue = new MyScoreValue(scoreCategory, scoreCategoryValue);
    }

    @Test
    public void testGetCategory() {
        assertEquals(scoreCategory, scoreValue.getCategory());
    }

    @Test
    public void testGetValue() {
        assertEquals(scoreCategoryValue, scoreValue.getValue());
    }

    @Test
    public void testSum() {
        Integer currentValue = scoreValue.getValue();
        scoreValue.sum(scoreValue);
        assertEquals(new Integer(currentValue + currentValue), scoreValue.getValue());
    }

    @Test
    public void testUpdateValue() {
        Integer newValue = new Integer(10);
        Integer currentValue = scoreValue.getValue();
        assertEquals(currentValue, scoreCategoryValue);
        scoreValue.updateValue(newValue);
        assertEquals(newValue, scoreValue.getValue());
    }
}
