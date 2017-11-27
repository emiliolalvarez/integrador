package com.unq.integrador.score;

import com.unq.integrador.score.category.ScoreCategory;
import com.unq.integrador.score.category.value.ScoreValue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Score {

    protected Set<ScoreValue> scores;

    public Score() {
        scores = new HashSet<>();
    }

    public Set<ScoreValue> getScoreValues() {
        return scores;
    }

    public Float getAverage() {
        return new Float(scores.stream().mapToInt(value -> value.getValue()).sum() / scores.size());
    }

    public boolean hasScoreValue(ScoreCategory category) {
        return scores.stream().anyMatch(scoreValue -> scoreValue.getCategory().getName().equals(category.getName()));
    }

    public ScoreValue getByScoreCategory(ScoreCategory category) {
        Optional<ScoreValue> scoreValue = scores.stream().filter(value -> value.getCategory().getName().equals(category.getName())).findFirst();
        return scoreValue.isPresent() ? scoreValue.get() : null;
    }

    protected void add(ScoreValue scoreValue) {
        ScoreValue current = getByScoreCategory(scoreValue.getCategory());
        if (current != null) {
            current.sum(scoreValue);
        } else {
            scores.add(scoreValue);
        }
    }
}
