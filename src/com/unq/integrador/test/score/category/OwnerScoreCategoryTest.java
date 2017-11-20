package com.unq.integrador.test.score.category;

import com.unq.integrador.score.category.OwnerScoreCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OwnerScoreCategoryTest {

    private OwnerScoreCategory ownerScoreCategory;
    private String name;

    @Before
    public void setUp() {
        name = "Category 1";
        ownerScoreCategory = new OwnerScoreCategory(name);
    }

    @Test
    public void testGetName() {
        assertEquals(name, ownerScoreCategory.getName());
    }
}
