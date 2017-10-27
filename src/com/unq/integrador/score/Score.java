package com.unq.integrador.score;

import com.unq.integrador.User;
import com.unq.integrador.score.category.ScoreCategory;

import java.util.HashMap;
import java.util.Map;

public abstract class Score {

    private User reviewer;
    private Map<ScoreCategory, Integer> scores;

    public Score(User reviewer) {
        this.reviewer = reviewer;
        scores = new HashMap<>();
    }

    public void addCategoryScore(ScoreCategory category, Integer score) {
        scores.put(category, score);
    }

    public Map<ScoreCategory, Integer> getScores() {
        return scores;
    }

    public float getAverage() {
        return scores.entrySet().stream().mapToInt(entry -> entry.getValue()).sum() / scores.size();
    }
}
