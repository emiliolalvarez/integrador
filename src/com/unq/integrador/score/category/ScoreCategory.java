package com.unq.integrador.score.category;

public abstract class ScoreCategory {

    private String name;

    public ScoreCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
