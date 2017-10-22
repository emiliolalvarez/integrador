package com.unq.integrador.search.filter;

import com.unq.integrador.search.filter.value.FilterValue;

public interface Filterable {
    FilterValue getFilterValue(String property);
}
