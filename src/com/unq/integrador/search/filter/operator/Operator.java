package com.unq.integrador.search.filter.operator;

public class Operator {

    private Operator() {};

    private static AndOperator andOperator = new AndOperator();
    private static OrOperator orOperator = new OrOperator();

    public static FilterOperator and() {
        return andOperator;
    }

    public static FilterOperator or() {
        return orOperator;
    }

}
