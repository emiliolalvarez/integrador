package com.unq.integrador.score;

import com.unq.integrador.user.User;
import com.unq.integrador.score.category.OwnerScoreCategory;

public class OwnerScore extends Score {


    public OwnerScore(User reviewer) {
        super(reviewer);
    }


    public void addCategoryScore(OwnerScoreCategory category, Integer score) {
        super.addCategoryScore(category, score);
    }
}
