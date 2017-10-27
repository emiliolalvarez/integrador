package com.unq.integrador.search;

import com.unq.integrador.search.value.FilterValue;

public abstract class SimpleFilter extends Filter {

    private String name;
    private FilterValue value;

    public SimpleFilter(String name, FilterValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public FilterValue getFilterValue() {
        return value;
    }

}
