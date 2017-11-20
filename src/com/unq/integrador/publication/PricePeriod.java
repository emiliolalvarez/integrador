package com.unq.integrador.publication;

import java.time.LocalDate;

public class PricePeriod {

    private Integer fromMonth;
    private Integer fromDay;
    private Integer endMonth;
    private Integer endDay;
    private float price;

    public PricePeriod(Integer fromMonth, Integer fromDay, Integer endMonth, Integer endDay, float price) {
        this.fromMonth = fromMonth;
        this.fromDay = fromDay;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.price = price;
    }

    public Integer getStartMonth() {
        return fromMonth;
    }

    public Integer getStartDay() {
        return fromDay;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean isInPeriod(LocalDate date) {
        return (getStartMonth() == date.getMonthValue() && getEndMonth() == date.getMonthValue()
                && getStartDay() <= date.getDayOfMonth() && date.getDayOfMonth() <= getEndDay())
                || (getStartMonth() == date.getMonthValue() && getEndMonth() > date.getMonthValue()
                && getStartDay() <= date.getDayOfMonth())
                || (getStartMonth() < date.getMonthValue() && date.getMonthValue() == getEndMonth()
                && date.getDayOfMonth() <= getEndDay())
                || (getStartMonth() < date.getMonthValue() && date.getMonthValue() < getEndMonth());
    }
}
