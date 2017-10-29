package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

public class OrFilter extends CompoundFilter {
    public OrFilter(Filter left, Filter right) {
        super(left, right);
    }

    @Override
    public Boolean eval(Publication publication) {
        return left.eval(publication) || right.eval(publication);
    }
}
