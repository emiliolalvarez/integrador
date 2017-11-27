package com.unq.integrador.user;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.GlobalScore;
import com.unq.integrador.score.category.value.ScoreValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class User {

    private String email;
    private String name;
    private String lastName;
    private String phone;
    private Set<Publication> publications;
    private List<Reservation> reservations;

    public User(String name, String lastName, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        publications = new HashSet<>();
        reservations = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Reservation> getFutureReservations() {
        return reservations.stream().filter(reservation -> LocalDate.now().isBefore(reservation.getStartDate()))
                .collect(Collectors.toList());
    }

    public List<Reservation> getCityReservations(String city) {
       return reservations.stream().filter(reservation -> reservation.getPublication().getProperty().getCity().equals(city))
               .collect(Collectors.toList());
    }

    public Set<String> getReservationsCities() {
       return this.getReservations().stream().map(reservation -> reservation.getPublication().getProperty().getCity())
               .collect(Collectors.toSet());
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    public void removePublication(Publication publication) {
        publications.remove(publication);
    }


     public GlobalScore getScoreAsOwner() {
        GlobalScore globalScore = new GlobalScore();
        Set<Reservation> reservations = getOwnerFinalizedReservations();
        reservations.forEach(reservation -> {
            reservation.getOwnerScore().getScoreValues().forEach(scoreValue -> {
                addToPartialScores(globalScore, scoreValue);
            });
        });
        calculateScoreAveragePerCategory(globalScore, reservations.size());
        return globalScore;
    }

    public GlobalScore getScoreAsOccupant() {
        GlobalScore globalScore = new GlobalScore();
        Set<Reservation> reservations = this.reservations.stream().filter(reservation -> reservation.isFinalized()).collect(Collectors.toSet());
        reservations.forEach(reservation -> {
            reservation.getOwnerScore().getScoreValues().forEach(scoreValue -> {
                addToPartialScores(globalScore, scoreValue);
            });
        });
        calculateScoreAveragePerCategory(globalScore, reservations.size());
        return globalScore;
    }

    private Set<Reservation> getOwnerFinalizedReservations() {
        Set<Reservation> reservations = new HashSet<>();
        publications.forEach(publication -> {
            publication.getReservations().stream().filter(reservation -> reservation.isFinalized())
                    .forEach(reservation -> reservations.add(reservation) );
        });
        return reservations;
    }


    private void addToPartialScores(GlobalScore globalScore, ScoreValue scoreValue) {
        if (globalScore.hasScoreValue(scoreValue.getCategory())) {
            globalScore.getByScoreCategory(scoreValue.getCategory()).sum(scoreValue);
        } else {
            globalScore.addScoreValue(scoreValue);
        }
    }

    private GlobalScore calculateScoreAveragePerCategory(GlobalScore globalScore, Integer total) {
        globalScore.getScoreValues().forEach(scoreValue -> {
            scoreValue.updateValue(Math.round(scoreValue.getValue() / total) );
        });
        return globalScore;
    }

}
