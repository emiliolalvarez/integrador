package com.unq.integrador.score.reviewer;

import com.unq.integrador.score.Score;
import com.unq.integrador.user.User;

public abstract class ReviewerScore extends Score {

    private User reviewer;
    private Comment comment;

    public ReviewerScore(User reviewer) {
        this.reviewer = reviewer;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getReviewer() {
        return reviewer;
    }

}
