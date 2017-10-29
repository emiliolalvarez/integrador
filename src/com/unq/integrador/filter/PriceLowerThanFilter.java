package com.unq.integrador.filter;

import com.unq.integrador.publication.Publication;
import java.time.LocalDate;

public class PriceLowerThanFilter implements Filter {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private float price;

    public PriceLowerThanFilter(float price, LocalDate startDate, LocalDate endDate) {
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Boolean eval(Publication publication) {
        return publication.getPrice(startDate, endDate) < price;
    }
}
