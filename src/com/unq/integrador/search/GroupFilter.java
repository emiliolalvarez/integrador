package com.unq.integrador.search;

import com.unq.integrador.search.operator.FilterOperator;

import java.util.ArrayList;
import java.util.List;

public class GroupFilter extends Filter {

    private List<Filter> filters;

    public GroupFilter() {
        filters = new ArrayList<>();
    }

    public GroupFilter addFilter(Filter filter, FilterOperator operator) {
        applyLogicOperator(filter, operator);
        filters.add(filter);
        return this;
    }

    private void applyLogicOperator(Filter filter, FilterOperator operator) {
        if (filters.size() > 0) {
            filter.setOperator(operator);
        }
    }

    @Override
    public String getConditionString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        filters.forEach((filter) -> sb.append(filter.getQueryString()));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean test(Filterable element) {
        boolean result = false;
        for (Integer i = 0; i < filters.size(); i++) {
            Filter filter = filters.get(i);
            if (filter.hasOperator()) {
                result = filter.getOperator().eval(result, filter.test(element));
            } else {
                result = filter.test(element);
            }
        }
        return result;
    }
}
