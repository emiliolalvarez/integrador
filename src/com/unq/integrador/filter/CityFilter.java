package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

public class CityFilter implements Filter {

    private String city;

    public CityFilter(String city) {
        this.city = city;
    }

    @Override
    public Boolean eval(Publication publication) {
        return publication.getCity().equals(city);
    }
}
