package com.unq.integrador.score;

import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.score.category.ScoreCategory;
import com.unq.integrador.score.category.value.ScoreValue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Score {


    private Set<ScoreValue> scores;

    public Score() {
        scores = new HashSet<>();
    }

    public void addScoreValue(ScoreValue value) {
        ScoreValue current = getByScoreCategory(value.getCategory());
        if (current != null) {
            current.sum(value);
        } else {
            scores.add(value);
        }
    }

    public Set<ScoreValue> getScoreValues() {
        return scores;
    }

    private ScoreValue getByScoreCategory(ScoreCategory category) {
        Optional<ScoreValue> scoreValue = scores.stream().filter(value -> value.getCategory().getName().equals(category.getName())).findFirst();
        return scoreValue.isPresent() ? scoreValue.get() : null;
    }

    public float getAverage() {
        return scores.stream().mapToInt(value -> value.getValue()).sum() / scores.size();
    }

 }
