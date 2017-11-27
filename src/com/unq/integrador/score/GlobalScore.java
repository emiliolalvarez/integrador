package com.unq.integrador.score;

import com.unq.integrador.score.category.ScoreCategory;
import com.unq.integrador.score.category.value.ScoreValue;
import java.util.Set;

public class GlobalScore extends Score {

    public void addScoreValue(ScoreValue scoreValue) {
       add(scoreValue);
    }



}
