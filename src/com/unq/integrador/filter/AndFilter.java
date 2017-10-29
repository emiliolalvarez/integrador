package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

public class AndFilter extends CompoundFilter {

    public AndFilter(Filter left, Filter right) {
        super(left, right);
    }

    @Override
    public Boolean eval(Publication publication) {
        return left.eval(publication) && right.eval(publication);
    }
}
