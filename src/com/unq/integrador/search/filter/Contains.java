package com.unq.integrador.search.filter;

import com.unq.integrador.search.filter.value.FilterValue;

public class Contains extends SimpleFilter {

    public Contains(String name, FilterValue filterValue) {
        super(name, filterValue);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " LIKE (%" + this.getFilterValue() + "%)";
    }

    @Override
    public boolean test(Filterable element) {
        return element.getFilterValue(this.getName()).toString().contains(this.getFilterValue().getValue().toString());
    }
}
