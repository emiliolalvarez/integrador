package com.unq.integrador.score.reviewer;

import com.unq.integrador.score.category.value.PropertyScoreValue;
import com.unq.integrador.score.reviewer.ReviewerScore;
import com.unq.integrador.user.User;

public class PropertyScore extends ReviewerScore {

    public PropertyScore(User reviewer) {
        super(reviewer);
    }

    public void addScoreValue(PropertyScoreValue value) {
        super.addScoreValue(value);
    }
}
