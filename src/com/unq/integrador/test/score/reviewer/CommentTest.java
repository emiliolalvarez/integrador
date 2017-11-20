package com.unq.integrador.test.score.reviewer;

import com.unq.integrador.score.reviewer.Comment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentTest {

    private Comment comment;
    private String text;


    @Before
    public void setUp() {
        text = "Some user comment";
        comment = new Comment(text);
    }

    @Test
    public void getCommentMessage() {
        assertEquals(text, comment.getComment());
    }

}
