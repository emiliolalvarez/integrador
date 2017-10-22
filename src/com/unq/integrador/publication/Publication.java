package com.unq.integrador.publication;

import java.time.LocalDateTime;
import java.util.Set;

public class Publication {

    String type;
    Integer surface;
    String country;
    String city;
    String address;
    Integer capacity;
    LocalDateTime checkout;
    LocalDateTime checkin;
    Set<Service> services;
    Set<Picture> pictures;
    Set<PaymentOption> paymentOptions;
    Set<PricePeriod> pricePeriods;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(Set<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public Set<PricePeriod> getPricePeriods() {
        return pricePeriods;
    }

    public void setPricePeriods(Set<PricePeriod> pricePeriods) {
        this.pricePeriods = pricePeriods;
    }


}
