package com.unq.integrador.search.filter.operator;

public class OrOperator extends FilterOperator {

    @Override
    public String getName() {
        return "OR";
    }

    @Override
    public Boolean eval(Boolean operation1, Boolean operation2) {
        return operation1 || operation2;
    }
}
