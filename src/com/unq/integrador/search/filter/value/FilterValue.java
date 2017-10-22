package com.unq.integrador.search.filter.value;

abstract public class FilterValue<V>{

    private V value;

    public FilterValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    abstract public Boolean isGreaterThan(FilterValue filterValue);
    abstract public Boolean isLessThan(FilterValue filterValue);

    public Boolean isEqualTo(FilterValue filterValue) {
        return this.value.equals(filterValue.getValue());
    }

}
