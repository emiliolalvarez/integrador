package com.unq.integrador.search.filter.value;

public class FilterFloatValue extends FilterValue {

    public FilterFloatValue(Float value) {
        super(value);
    }

    public Float value() {
        return (Float) super.value();
    }

    @Override
    public Boolean isGreaterThan(FilterValue value) {
        return ((Float)value.value()).compareTo(value()) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue value) {
        return ((Float)value.value()).compareTo(value()) == -1;
    }

}
