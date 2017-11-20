package com.unq.integrador.test.publication;

import com.unq.integrador.publication.Picture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PictureTest {

    private String filePath;
    private Picture picture;

    @Before
    public void setUp() {
        filePath = "picture.jpg";
        picture = new Picture(filePath);
    }

    @Test
    public void testGetFilePath() {
        assertEquals(filePath, picture.getFilePath());
    }
}
