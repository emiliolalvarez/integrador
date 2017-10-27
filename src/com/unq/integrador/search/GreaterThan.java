package com.unq.integrador.search;

import com.unq.integrador.search.value.FilterValue;

public class GreaterThan extends SimpleFilter {

    public GreaterThan(String name, FilterValue filterValue) {
        super(name, filterValue);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " > " + this.getFilterValue().getValue();
    }

    @Override
    public boolean test(Filterable element) {
        return element.getFilterValue(this.getName()).isGreaterThan(this.getFilterValue());
    }
}
