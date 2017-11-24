package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;

import java.time.LocalDate;

public class AvailableFilter implements Filter {

    LocalDate startDate, endDate;

    public AvailableFilter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Boolean eval(Publication publication) {
        return publication.isAvailable(startDate, endDate);
    }
}
