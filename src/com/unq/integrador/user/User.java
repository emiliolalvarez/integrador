package com.unq.integrador.user;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.OccupantScore;
import com.unq.integrador.score.OwnerScore;
import com.unq.integrador.score.Score;
import com.unq.integrador.score.category.ScoreCategory;

import java.time.LocalDate;
import java.util.*;
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

    public Score getScoreAsOwner() {
        Map<ScoreCategory, Integer> partials = new HashMap<>();
        publications.forEach(publication -> {
            publication.getReservations().forEach(reservation -> {
                addToPartialScores(partials, reservation.getOwnerScore());
            });
        });
        return calculateScoreAveragePerCategory(partials, new OwnerScore(this));
    }

    public Score getScoreAsOccupant() {
        Map<ScoreCategory, Integer> partials = new HashMap<>();
        reservations.forEach(reservation -> {
            addToPartialScores(partials, reservation.getOccupantScore());
        });
        return calculateScoreAveragePerCategory(partials, new OccupantScore(this));
    }

    private void addToPartialScores(Map<ScoreCategory, Integer> partials, Score score) {
        score.getScores().entrySet().stream().forEach(entry -> {
            ScoreCategory category = entry.getKey();
            Integer value = entry.getValue();
            partials.put(category, partials.get(category) + value);
        });
    }

    private Score calculateScoreAveragePerCategory(Map<ScoreCategory, Integer> partials, Score score) {
        partials.forEach((scoreCategory, value) -> {
            score.addCategoryScore(scoreCategory, Math.round(value / partials.size()));
        });
        return score;
    }

}
