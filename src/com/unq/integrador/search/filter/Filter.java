package com.unq.integrador.search.filter;


import com.unq.integrador.search.filter.operator.FilterOperator;

abstract public class Filter {

    FilterOperator operator = null;

    abstract protected String getConditionString();

    public String getQueryString    () {
        return (operator != null ? " " + operator.getName() + " " : "") + getConditionString();
    }

    public void setOperator(FilterOperator operation) {
        this.operator = operation;
    }

    public Boolean hasOperator() {
        return operator != null;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    abstract public boolean test(Filterable element);
}
