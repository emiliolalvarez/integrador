package com.unq.integrador.search.filter.value;

public class FilterFloatValue extends FilterValue {

    public FilterFloatValue(Float value) {
        super(value);
    }

    public Float getValue() {
        return (Float) super.getValue();
    }

    @Override
    public Boolean isGreaterThan(FilterValue filterValue) {
        return getValue().compareTo(((Float) filterValue.getValue())) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue filterValue) {
        return getValue().compareTo(((Float) filterValue.getValue())) == -1;
    }

}
