package com.unq.integrador.search.operator;

abstract public class FilterOperator {
    public abstract String getName();
    public abstract Boolean eval(Boolean operation1, Boolean operation2);
}
