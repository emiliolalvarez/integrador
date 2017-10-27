package com.unq.integrador.search.operator;

public class AndOperator extends FilterOperator {

    @Override
    public String getName() {
        return "AND";
    }

    @Override
    public Boolean eval(Boolean operation1, Boolean operation2) {
            return operation1 && operation2;
    }

}
