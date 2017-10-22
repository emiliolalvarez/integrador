package com.unq.integrador.search.filter.value;

abstract public class FilterValue<V>{

    private V value;

    public FilterValue(V value) {
        this.value = value;
    }

    public V value() {
        return value;
    }

    abstract public Boolean isGreaterThan(FilterValue value);
    abstract public Boolean isLessThan(FilterValue value);

    public Boolean isEqualTo(FilterValue value) {
        return this.value.equals(value.value());
    }

}
