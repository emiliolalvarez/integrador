package com.unq.integrador.publication;

import com.unq.integrador.reservation.ReservationService;
import com.unq.integrador.search.Filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public class PublicationService {

    private Set<Publication> publications;
    private ReservationService reservationService;
    public PublicationService(Set<Publication> publications, ReservationService reservationService) {
        this.reservationService = reservationService;
        this.publications = publications;
    }

    public Set<Publication> getAllPublications() {
        return publications;
    };

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
    }

    public Set<Publication> search(Filter filter) {
        LocalDate startDate = LocalDate.parse("27/09/2017",  DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("27/09/2017",  DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return publications.stream().filter(publication -> filter.test(new FilterablePublication(publication, reservationService, startDate, endDate)))
                .collect(Collectors.toSet()
        );
    }

}
