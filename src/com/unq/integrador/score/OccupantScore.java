package com.unq.integrador.score;

import com.unq.integrador.user.User;
import com.unq.integrador.score.category.OccupantScoreCategory;

public class OccupantScore extends Score {

    public OccupantScore(User reviewer) {
        super(reviewer);
    }

    public void addCategoryScore(OccupantScoreCategory category, Integer score) {
        super.addCategoryScore(category, score);
    }
}
