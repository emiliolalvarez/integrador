package com.unq.integrador.test.score;

import com.unq.integrador.score.Score;
import com.unq.integrador.score.category.ScoreCategory;
import com.unq.integrador.score.category.value.ScoreValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreTest {

    private Score score;
    private ScoreCategory scoreCategory;
    private Integer scoreCategoryValue;
    private ScoreValue scoreValue;
    private String scoreCategoryName;

    @Before
    public void setUp() {
        score = new Score();
        scoreCategoryValue = 5;
        scoreCategoryName = "Category 1";
        scoreCategory = getScoreCategoryMock(scoreCategoryName);
        scoreValue = getScoreValueMock();

    }

    @Test
    public void testAddingNewScoreValue() {
        assertEquals(score.getScoreValues().size(), 0);
        score.addScoreValue(scoreValue);
        assertEquals(score.getScoreValues().size(), 1);
        assertTrue(score.getScoreValues().contains(scoreValue));
    }

    @Test
    public void testAddingExistentScoreValueWillUpdateScoreValue() {
        ScoreValue newScoreValue = mock(ScoreValue.class);
        when(newScoreValue.getCategory()).thenReturn(scoreCategory);
        score.addScoreValue(scoreValue);
        assertEquals(new Integer(5), scoreCategoryValue);
        score.addScoreValue(newScoreValue);
        verify(scoreValue).sum(newScoreValue);
        assertEquals(1, score.getScoreValues().size());
    }

    @Test
    public void testGetAverage() {
        ScoreValue scoreValue2 = mock(ScoreValue.class);
        ScoreCategory scoreCategory2 = getScoreCategoryMock("Other category");
        when(scoreValue2.getCategory()).thenReturn(scoreCategory2);
        when(scoreValue2.getValue()).thenReturn(scoreCategoryValue - 1);
        score.addScoreValue(scoreValue);
        score.addScoreValue(scoreValue2);
        assertEquals(new Float((scoreCategoryValue + scoreCategoryValue - 1) / 2 ), score.getAverage());
    }

    private ScoreValue getScoreValueMock() {
        ScoreValue scoreValue = mock(ScoreValue.class);
        when(scoreValue.getCategory()).thenReturn(scoreCategory);
        when(scoreValue.getValue()).thenReturn(scoreCategoryValue);
        return scoreValue;
    }

    private ScoreCategory getScoreCategoryMock(String scoreCategoryName) {
        ScoreCategory category = mock(ScoreCategory.class);
        when(category.getName()).thenReturn(scoreCategoryName);
        return category;
    }


}
