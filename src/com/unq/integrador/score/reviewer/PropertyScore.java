package com.unq.integrador.score.reviewer;

import com.unq.integrador.score.category.value.PropertyScoreValue;
import com.unq.integrador.score.category.value.ScoreValue;
import com.unq.integrador.user.User;

public class PropertyScore extends ReviewerScore {


    public void addScoreValue(PropertyScoreValue scoreValue) {
        add(scoreValue);
    }

    public PropertyScore(User reviewer) {
        super(reviewer);
    }


}
