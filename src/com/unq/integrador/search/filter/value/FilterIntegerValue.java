package com.unq.integrador.search.filter.value;

public class FilterIntegerValue extends FilterValue {

    public FilterIntegerValue(Integer value) {
        super(value);
    }

    public Integer value() {
        return (Integer) super.value();
    }

    @Override
    public Boolean isGreaterThan(FilterValue value) {
        return ((Integer)value.value()).compareTo(value()) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue value) {
        return ((Integer)value.value()).compareTo(value()) == -1;
    }

}
