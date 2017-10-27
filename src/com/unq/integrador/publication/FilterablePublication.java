package com.unq.integrador.publication;

import com.unq.integrador.reservation.ReservationService;
import com.unq.integrador.search.Filterable;
import com.unq.integrador.search.value.FilterValue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;

public class FilterablePublication implements Filterable {

    private final ReservationService reservationService;
    private Publication publication;
    private LocalDate startDate, endDate;

    public FilterablePublication(Publication publication, ReservationService reservationService,
                                 LocalDate startDate, LocalDate endDate) {
        this.publication = publication;
        this.reservationService =reservationService;
    }

    @Override
    public FilterValue getFilterValue(String property) {
        if (property.equals("country")) {
            return new FilterValue(publication.getCountry());
        } else if(property.equals("surface")) {
            return new FilterValue(publication.getSurface());
        } else if(property.equals("city")) {
            return new FilterValue(publication.getCity());
        } else if(property.equals("address")) {
            return new FilterValue(publication.getAddress());
        } else if(property.equals("type")) {
            return new FilterValue(publication.getType().getName());
        } else if(property.equals("capacity")) {
              return new FilterValue(publication.getCapacity());
        } else if(property.equals("status")) {
            return new FilterValue(reservationService.getPublicationStatusForDaterRange(publication, startDate, endDate).toString());
        } else {
            throw new UncheckedIOException(new IOException("Unsupported property filter"));
        }
    }
}
