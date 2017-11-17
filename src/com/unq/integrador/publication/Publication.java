package com.unq.integrador.publication;

import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.user.User;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.site.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Publication implements PublicationSubject {
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
    List<HomePagePublisher> publishers;
    List<PopUpWindow> applications;


    public Publication(User owner) {
        this.owner = owner;
        reservations = new ArrayList<>();
        pricePeriods = new HashSet<>();
        paymentOptions = new HashSet<>();
        services = new HashSet<>();
        pictures = new HashSet<>();
        publishers = new ArrayList<>();
        applications = new ArrayList<>();
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

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

    public void removePicture(Picture picture) {
        pictures.remove(picture);
    }

    public Set<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(Set<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public void addPaymentOption(PaymentOption paymentOption) {
        this.paymentOptions.add(paymentOption);
    }

    public void removePaymentOption(PaymentOption paymentOption) {
        paymentOptions.remove(paymentOption);
    }

    public Set<PricePeriod> getPricePeriods() {
        return pricePeriods;
    }

    public void setPricePeriods(Set<PricePeriod> pricePeriods) {
        this.pricePeriods = pricePeriods;
    }

    public void addPricePeriod(PricePeriod pricePeriod) {
        pricePeriods.add(pricePeriod);
    }

    public void removePicePeriod(PricePeriod pricePeriod) {
        pricePeriods.remove(pricePeriod);
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

    public float getPrice(LocalDate startDate, LocalDate endDate) {
        float amount = 0;
        LocalDate currentDay = startDate.plusDays(0);
        while (!currentDay.isAfter(endDate)) {
            amount+=getDayPrice(currentDay);
            currentDay = currentDay.plusDays(1);
        }
        return  amount;
    }

    public void modifyPrice(PricePeriod pricePeriod, Float price) {
        if (pricePeriod.getPrice() > price) {
            this.notifyPriceChange(price);
        }
        pricePeriod.setPrice(price);
    }

    public void notifyPriceChange(Float price) {
        this.publishers.forEach(publisher -> publisher.publish("No te pierdas esta oferta: Un inmueble "
                + this.getType().getName() +" a tan sólo " + price +" pesos"));
    }

    public void notifyCancelledReservation() {
        this.applications.forEach(application -> application.popUp("El/la " + this.getType().getName()
                + " que te interesa se ha liberado! Corre a reservarlo!", "green", 14));
    }

    public void registerPriceObserver(HomePagePublisher publisher) {
        this.publishers.add(publisher);
    }

    public void registerReservationCancelledObserver(PopUpWindow application) {
        this.applications.add(application);
    }

    private float getDayPrice(LocalDate date) {
        Optional<PricePeriod> result = getPricePeriods().stream().filter(pricePeriod ->
                pricePeriod.getFromMonth() <= (date.getMonthValue() +1 )
                        && pricePeriod.getFromDay()<= date.getDayOfMonth()
                        && (date.getMonthValue() + 1)<= pricePeriod.getEndMonth() && date.getDayOfMonth() <= pricePeriod.getEndDay()
        ).findFirst();
        return result.isPresent() ? result.get().getPrice() : 0;
    }

}
