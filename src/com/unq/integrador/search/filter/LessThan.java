package com.unq.integrador.search.filter;

import com.unq.integrador.search.filter.value.FilterValue;

public class LessThan extends SimpleFilter {
    public LessThan(String name, FilterValue value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " < " + this.getFilterValue().getValue();
    }

    @Override
    public boolean test(Filterable element) {
        return element.getFilterValue(this.getName()).isLessThan(this.getFilterValue());
    }
}
