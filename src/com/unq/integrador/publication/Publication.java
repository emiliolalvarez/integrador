package com.unq.integrador.publication;

import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.GlobalScore;
import com.unq.integrador.score.reviewer.Comment;
import com.unq.integrador.site.NotificationManager;
import com.unq.integrador.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Publication implements PublicationSubject {

    private Property property;
    private LocalTime checkOut;
    private LocalTime checkIn;
    private Set<PaymentOption> paymentOptions;
    private Set<PricePeriod> pricePeriods;
    private List<Reservation> reservations;
    private User owner;
    private NotificationManager notificationManager;

    public Publication(User owner, Property property) {
        this.owner = owner;
        reservations = new ArrayList<>();
        pricePeriods = new HashSet<>();
        paymentOptions = new HashSet<>();
        notificationManager = new NotificationManager();
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
            this.notifyPriceLowered(price);
        }
        pricePeriod.setPrice(price);
    }

    public void notifyPriceLowered(Float price) {
        this.notificationManager.notifyPriceLowered(this, price);
    }

    public void notifyCancelledReservation() {
        notificationManager.notifyReservationCancelled(this);
    }

    public GlobalScore getPropertyScore() {
        GlobalScore globalScore = new GlobalScore();
        List<Reservation> reservations = this.reservations.stream().filter(reservation -> reservation.getPropertyScore() != null).collect(Collectors.toList());
        Integer totalScores = reservations.size();
        reservations.stream().filter(reservation -> reservation.getPropertyScore() != null).forEach(reservation -> {
            reservation.getPropertyScore().getScoreValues().forEach(scoreValue -> {
                globalScore.addScoreValue(scoreValue);
            });
        });
        globalScore.getScoreValues().forEach(scoreValue -> scoreValue.updateValue(Math.round(scoreValue.getValue() / totalScores)));
        return globalScore;
    }

    public void register(PriceLoweredObserver observer) {
        notificationManager.register(this, observer);
    }
    public void register(ReservationCancelledObserver observer) {
        notificationManager.register(this, observer);
    }

    public Boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        return !reservations.stream().filter(reservation -> reservation.isAccepted()).anyMatch(reservation ->
            reservation.getStartDate().equals(startDate) || reservation.getEndDate().equals(endDate)
            || reservation.getStartDate().equals(endDate) || reservation.getEndDate().equals(endDate)
            || (reservation.getStartDate().isAfter(startDate) && reservation.getEndDate().isBefore(endDate))
            || (reservation.getStartDate().isBefore(startDate) && reservation.getEndDate().isAfter(startDate))
            || (reservation.getStartDate().isBefore(endDate) && reservation.getEndDate().isAfter(endDate))
        );
    }

    public List<Reservation> getFinalizedReservations() {
        return reservations.stream().filter(reservation -> reservation.isFinalized()).collect(Collectors.toList());
    }

    public List<Comment> getPropertyComments() {
        return getFinalizedReservations().stream().map(reservation -> reservation.getPropertyScore().getComment())
                .collect(Collectors.toList());
    }
}
