package com.unq.integrador.publication;

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

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getFromDay() {
        return fromDay;
    }

    public void setFromDay(Integer fromDay) {
        this.fromDay = fromDay;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
