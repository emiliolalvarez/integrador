package com.unq.integrador.test.score.reviewer;

import com.unq.integrador.score.reviewer.Comment;
import com.unq.integrador.score.reviewer.ReviewerScore;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ReviewerScoreTest {

    private ReviewerScore reviewerScore;
    private User reviewer;
    private Comment comment;

    @Before
    public void setUp() {
        reviewer = mock(User.class);
        comment = mock(Comment.class);
        class MyReviewerScore extends ReviewerScore {
            public MyReviewerScore(User reviewer) {
                super(reviewer);
            }
        };
        reviewerScore = spy(new MyReviewerScore(reviewer));
    }

    @Test
    public void testGetAndSetComment() {
        assertNull(reviewerScore.getComment());
        reviewerScore.setComment(comment);
        assertEquals(comment, reviewerScore.getComment());
    }

    @Test
    public void tesGetReviwer() {
        assertEquals(reviewer, reviewerScore.getReviewer());
    }

}
