package com.unq.integrador.search.filter.value;

public class FilterStringValue extends FilterValue {

    public FilterStringValue(String value) {
        super(value);
    }

    public String value() {
        return (String) super.value();
    }

    @Override
    public Boolean isGreaterThan(FilterValue value) {
        return ((String)value.value()).compareTo(value()) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue value) {
        return ((String)value.value()).compareTo(value()) == -1;
    }
}
