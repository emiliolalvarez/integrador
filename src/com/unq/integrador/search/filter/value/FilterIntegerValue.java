package com.unq.integrador.search.filter.value;

public class FilterIntegerValue extends FilterValue {

    public FilterIntegerValue(Integer value) {
        super(value);
    }

    public Integer getValue() {
        return (Integer) super.getValue();
    }

    @Override
    public Boolean isGreaterThan(FilterValue filterValue) {
        return getValue().compareTo(((Integer) filterValue.getValue())) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue filterValue) {
        return getValue().compareTo(((Integer) filterValue.getValue())) == -1;
    }

}
