package com.unq.integrador.search.filter.operator;

import com.unq.integrador.search.filter.Filterable;

abstract public class FilterOperator {
    public abstract String getName();
    public abstract Boolean eval(Boolean operation1, Boolean operation2);
}
