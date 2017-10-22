package com.unq.integrador.search.filter.value;

public class FilterStringValue extends FilterValue {

    public FilterStringValue(String value) {
        super(value);
    }

    public String getValue() {
        return (String) super.getValue();
    }

    @Override
    public Boolean isGreaterThan(FilterValue filterValue) {
        return getValue().compareTo(((String) filterValue.getValue())) == 1;
    }

    @Override
    public Boolean isLessThan(FilterValue filterValue) {
        return getValue().compareTo(((String) filterValue.getValue())) == -1;
    }
}
