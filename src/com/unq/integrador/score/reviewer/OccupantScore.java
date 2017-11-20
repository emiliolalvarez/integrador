package com.unq.integrador.score.reviewer;

import com.unq.integrador.score.category.value.OccupantScoreValue;
import com.unq.integrador.score.reviewer.ReviewerScore;
import com.unq.integrador.user.User;

public class OccupantScore extends ReviewerScore {

    public OccupantScore(User reviewer) {
        super(reviewer);
    }

    public void addScoreValue(OccupantScoreValue value) {
        super.addScoreValue(value);
    }
}
