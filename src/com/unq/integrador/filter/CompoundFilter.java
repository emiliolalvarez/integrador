package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

public abstract class CompoundFilter implements Filter {

    protected Filter left, right;

    public CompoundFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }

}
