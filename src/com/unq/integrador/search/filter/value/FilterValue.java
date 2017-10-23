package com.unq.integrador.search.filter.value;

public class FilterValue {

    private Comparable value;

    public FilterValue(Comparable value) {
        this.value = value;
    }

    public Comparable getValue() {
        return value;
    }

    public Boolean isLessThan(FilterValue filterValue) {
        return getValue().compareTo((filterValue.getValue())) == -1;
    }

    public Boolean isGreaterThan(FilterValue filterValue) {
        return getValue().compareTo((filterValue.getValue())) == 1;
    }

    public Boolean isEqualTo(FilterValue filterValue) {
        return this.value.equals(filterValue.getValue());
    }

}
