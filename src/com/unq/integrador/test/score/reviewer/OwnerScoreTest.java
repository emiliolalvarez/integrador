package com.unq.integrador.test.score.reviewer;

import com.unq.integrador.score.reviewer.OwnerScore;
import com.unq.integrador.score.category.value.OwnerScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class OwnerScoreTest {

    private OwnerScore ownerScore;
    private User reviewer;

    @Before
    public void setUp() {
        reviewer = mock(User.class);
        ownerScore = new OwnerScore(reviewer);
    }

    @Test
    public void testAddScoreValue() {
        ScoreValue scoreValue = mock(OwnerScoreValue.class);
        assertEquals(0, ownerScore.getScoreValues().size());
        ownerScore.addScoreValue(scoreValue);
        assertEquals(1, ownerScore.getScoreValues().size());
        assertTrue(ownerScore.getScoreValues().contains(scoreValue));
    }
}
