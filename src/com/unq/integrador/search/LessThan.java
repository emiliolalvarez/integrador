package com.unq.integrador.search;

import com.unq.integrador.search.value.FilterValue;

public class LessThan extends SimpleFilter {
    public LessThan(String name, FilterValue filterValue) {
        super(name, filterValue);
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
