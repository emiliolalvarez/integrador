package com.unq.integrador.score;

import com.unq.integrador.user.User;
import com.unq.integrador.score.category.PropertyScoreCategory;

public class PropertyScore extends Score {

    public PropertyScore(User reviewer) {
        super(reviewer);
    }

    public void addCategoryScore(PropertyScoreCategory category, Integer score) {
        super.addCategoryScore(category, score);
    }
}
