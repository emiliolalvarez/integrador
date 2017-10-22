package com.unq.integrador.search.filter;

import com.unq.integrador.search.filter.value.FilterValue;

public class Contains extends SimpleFilter {

    public Contains(String name, FilterValue value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " LIKE (%" + this.getFilterValue() + "%)";
    }

    @Override
    public boolean test(Filterable element) {
        return element.getFilterValue(this.getName()).toString().contains(this.getFilterValue().value().toString());
    }
}
