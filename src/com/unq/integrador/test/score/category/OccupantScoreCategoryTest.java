package com.unq.integrador.test.score.category;

import com.unq.integrador.score.category.OccupantScoreCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OccupantScoreCategoryTest {

    private OccupantScoreCategory occupantScoreCategory;
    private String name;

    @Before
    public void setUp() {
        name = "Category 1";
        occupantScoreCategory = new OccupantScoreCategory(name);
    }

    @Test
    public void testGetName() {
        assertEquals(name, occupantScoreCategory.getName());
    }
}
