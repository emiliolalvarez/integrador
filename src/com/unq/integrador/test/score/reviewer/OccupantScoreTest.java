package com.unq.integrador.test.score.reviewer;

import com.unq.integrador.score.reviewer.OccupantScore;
import com.unq.integrador.score.category.value.OccupantScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class OccupantScoreTest {

    private OccupantScore occupantScore;
    private User reviewer;

    @Before
    public void setUp() {
        reviewer = mock(User.class);
        occupantScore = new OccupantScore(reviewer);
    }

    @Test
    public void testAddScoreValue() {
        ScoreValue scoreValue = mock(OccupantScoreValue.class);
        assertEquals(0, occupantScore.getScoreValues().size());
        occupantScore.addScoreValue(scoreValue);
        assertEquals(1, occupantScore.getScoreValues().size());
        assertTrue(occupantScore.getScoreValues().contains(scoreValue));
    }
}
