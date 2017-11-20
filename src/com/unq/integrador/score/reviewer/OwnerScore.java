package com.unq.integrador.score.reviewer;

import com.unq.integrador.score.category.value.OwnerScoreValue;
import com.unq.integrador.score.reviewer.ReviewerScore;
import com.unq.integrador.user.User;

public class OwnerScore extends ReviewerScore {


    public OwnerScore(User reviewer) {
        super(reviewer);
    }


    public void addScoreValue(OwnerScoreValue value) {
        super.addScoreValue(value);
    }
}
