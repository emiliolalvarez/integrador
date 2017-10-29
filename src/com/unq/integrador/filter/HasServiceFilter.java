package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.Service;

public class HasServiceFilter implements Filter {

    private final Service service;

    public HasServiceFilter(Service service) {
        this.service = service;
    }

    @Override
    public Boolean eval(Publication publication) {
        return publication.getServices().stream().filter(srv -> srv.getName().equals(service.getName())).count() > 0;
    }
}
