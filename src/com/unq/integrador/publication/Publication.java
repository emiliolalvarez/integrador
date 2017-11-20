package com.unq.integrador.publication;

import com.unq.integrador.score.Score;
import com.unq.integrador.site.HomePagePublisher;
import com.unq.integrador.site.PopUpWindow;
import com.unq.integrador.user.User;
import com.unq.integrador.reservation.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Publication implements PublicationSubject {

    private Property property;
    private LocalTime checkOut;
    private LocalTime checkIn;
    private Set<PaymentOption> paymentOptions;
    private Set<PricePeriod> pricePeriods;
    private List<Reservation> reservations;
    private User owner;
    private List<HomePagePublisher> publishers;
    private List<PopUpWindow> applications;

    public Publication(User owner, Property property) {
        this.owner = owner;
        reservations = new ArrayList<>();
        pricePeriods = new HashSet<>();
        paymentOptions = new HashSet<>();
        publishers = new ArrayList<>();
        applications = new ArrayList<>();
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkout) {
        this.checkOut = checkout;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkin) {
        this.checkIn = checkin;
    }

    public Set<PaymentOption> getPaymentOptions() {
        return paymentOptions;
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

    public void addPricePeriod(PricePeriod pricePeriod) {
        pricePeriods.add(pricePeriod);
    }

    public void removePricePeriod(PricePeriod pricePeriod) {
        pricePeriods.remove(pricePeriod);
    }

    public User getOwner() {
        return owner;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.getOccupant().addReservation(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Float getPrice(LocalDate startDate, LocalDate endDate) {
        float amount = 0;
        LocalDate currentDay = startDate.plusDays(0);
        while (!currentDay.isAfter(endDate)) {
            amount+=getDayPrice(currentDay);
            currentDay = currentDay.plusDays(1);
        }
        return amount;
    }

    public Float getDayPrice(LocalDate date) {
        Optional<PricePeriod> result = getPricePeriods().stream()
                .filter(pricePeriod -> pricePeriod.isInPeriod(date)).findFirst();
        return result.isPresent() ? result.get().getPrice() : 0;
    }

    public void modifyPrice(PricePeriod pricePeriod, Float price) {
        if (pricePeriod.getPrice() > price) {
            this.notifyPriceChange(price);
        }
        pricePeriod.setPrice(price);
    }

    public void notifyPriceChange(Float price) {
        this.publishers.forEach(publisher -> publisher.publish("No te pierdas esta oferta: Un inmueble "
                + this.property.getType().getName() +" a tan sólo " + price +" pesos"));
    }

    public void notifyCancelledReservation() {
        this.applications.forEach(application -> application.popUp("El/la " + this.property.getType().getName()
                + " que te interesa se ha liberado! Corre a reservarlo!", "green", 14));
    }

    public Score getPropertyScore() {
        Score globalScore = new Score();
        Long totalScores = reservations.stream().filter(reservation -> reservation.getPropertyScore() != null).count();
        reservations.stream().filter(reservation -> reservation.getPropertyScore() != null).forEach(reservation -> {
            reservation.getPropertyScore().getScoreValues().forEach(scoreValue -> {
                globalScore.addScoreValue(scoreValue);
            });
        });
        globalScore.getScoreValues().forEach(scoreValue -> scoreValue.updateValue(Math.round(scoreValue.getValue() / totalScores)));
        return globalScore;
    }

    public void registerPriceObserver(HomePagePublisher publisher) {
        this.publishers.add(publisher);
    }

    public void registerReservationCancelledObserver(PopUpWindow application) {
        this.applications.add(application);
    }

}
