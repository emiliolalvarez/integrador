package com.unq.integrador.score.category.value;

import com.unq.integrador.score.category.ScoreCategory;

public abstract class ScoreValue {

    private Integer value;
    private ScoreCategory category;

    public ScoreValue(ScoreCategory category, Integer value) {
        this.category = category;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public ScoreCategory getCategory() {
        return category;
    }

    public void sum(ScoreValue scoreValue) {
        this.value+=scoreValue.getValue();
    }

    public void updateValue(Integer value) {
        this.value = value;
    }
}
