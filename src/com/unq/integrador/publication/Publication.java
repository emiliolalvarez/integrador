package com.unq.integrador.publication;

import com.unq.integrador.User;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.site.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Publication {
    PropertyType type;
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
    List<Reservation> reservations;
    User owner;

    public Publication(User owner) {
        this.owner = owner;
        reservations = new ArrayList<>();
        pricePeriods = new HashSet<>();
        paymentOptions = new HashSet<>();
        services = new HashSet<>();
        pictures = new HashSet<>();
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.getOccupant().addReservation(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
