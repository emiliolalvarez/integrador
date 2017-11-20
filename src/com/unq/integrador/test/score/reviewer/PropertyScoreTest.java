package com.unq.integrador.test.score.reviewer;

import com.unq.integrador.score.reviewer.PropertyScore;
import com.unq.integrador.score.category.value.PropertyScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PropertyScoreTest {

    private PropertyScore propertyScore;
    private User reviewer;

    @Before
    public void setUp() {
        reviewer = mock(User.class);
        propertyScore = new PropertyScore(reviewer);
    }

    @Test
    public void testAddScoreValue() {
        ScoreValue scoreValue = mock(PropertyScoreValue.class);
        assertEquals(0, propertyScore.getScoreValues().size());
        propertyScore.addScoreValue(scoreValue);
        assertEquals(1, propertyScore.getScoreValues().size());
        assertTrue(propertyScore.getScoreValues().contains(scoreValue));
    }
}
