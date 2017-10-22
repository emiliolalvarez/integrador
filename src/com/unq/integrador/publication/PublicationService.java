package com.unq.integrador.publication;

import com.unq.integrador.search.filter.Filter;

import java.util.Set;
import java.util.stream.Collectors;

public class PublicationService {

    Set<Publication> publications;

    public PublicationService(Set<Publication> publications) {
        this.publications = (publications);
    }

    public Set<Publication> getAllPublications() {
        return publications;
    };

    public Set<Publication> search(Filter filter) {
        return publications.stream().filter(publication -> filter.test(new FilterablePublication(publication)))
                .collect(Collectors.toSet()
        );
    }
}
