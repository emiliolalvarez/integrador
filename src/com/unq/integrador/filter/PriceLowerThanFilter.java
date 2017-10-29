package com.unq.integrador.filter;

import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.publication.Publication;

import java.time.LocalDate;
import java.util.Optional;

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
        return getPriceAverage(startDate, endDate, publication) < price;
    }


    private float getPriceAverage(LocalDate startDate, LocalDate endDate, Publication publication) {
        float amount = 0;
        LocalDate initDate = startDate.plusDays(0);
        while (!initDate.isAfter(endDate)) {
            amount+=getDayPrice(initDate, publication);
            initDate = initDate.plusDays(1);
        }
        return  amount;
    }

    private float getDayPrice(LocalDate date, Publication publication) {
        Optional<PricePeriod> result = publication.getPricePeriods().stream().filter(pricePeriod ->
                pricePeriod.getFromMonth() <= (date.getMonthValue() +1 )
                && pricePeriod.getFromDay()<= date.getDayOfMonth()
                && (date.getMonthValue() + 1)<= pricePeriod.getEndMonth() && date.getDayOfMonth() <= pricePeriod.getEndDay()
        ).findFirst();
        return result.isPresent() ? result.get().getPrice() : 0;
    }
}
