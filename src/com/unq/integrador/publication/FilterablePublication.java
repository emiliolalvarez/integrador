package com.unq.integrador.publication;

import com.unq.integrador.search.filter.Filterable;
import com.unq.integrador.search.filter.value.FilterIntegerValue;
import com.unq.integrador.search.filter.value.FilterStringValue;
import com.unq.integrador.search.filter.value.FilterValue;

import java.io.IOException;
import java.io.UncheckedIOException;

public class FilterablePublication implements Filterable {

    private Publication publication;

    public FilterablePublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public FilterValue getFilterValue(String property) {
        if (property.equals("country")) {
            return new FilterStringValue(this.publication.getCountry());
        } else if(property.equals("city")) {
            return new FilterStringValue(this.publication.getCity());
        } else if(property.equals("surface")) {
            return new FilterIntegerValue(this.publication.getSurface());
        } else if(property.equals("capacity")) {
            return new FilterIntegerValue(this.publication.getCapacity());
        } else {
            throw new UncheckedIOException(new IOException("Unsupported property filter"));
        }
    }
}
