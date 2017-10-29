package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

public class CountryFilter implements Filter {

    private final String country;

    public CountryFilter(String country) {
        this.country = country;
    }

    @Override
    public Boolean eval(Publication publication) {
        return publication.getCountry().equals(country);
    }
}
