package com.unq.integrador.search;

import com.unq.integrador.search.value.FilterValue;

public interface Filterable {
    FilterValue getFilterValue(String property);
}
