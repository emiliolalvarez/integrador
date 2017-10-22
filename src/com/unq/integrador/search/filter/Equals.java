package com.unq.integrador.search.filter;

import com.unq.integrador.search.filter.value.FilterValue;

public class Equals extends SimpleFilter {

    public Equals(String name, FilterValue value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " = \"" + this.getFilterValue().getValue() + "\"";
    }

    @Override
    public boolean test(Filterable element) {
        if (element.getFilterValue(this.getName()).getValue() == null) {
            return false;
        }
        return element.getFilterValue(this.getName()).isEqualTo(this.getFilterValue());
    }

}
