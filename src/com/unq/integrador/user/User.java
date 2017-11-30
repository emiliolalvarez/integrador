package com.unq.integrador.user;

import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.GlobalScore;
import com.unq.integrador.score.category.value.ScoreValue;
import org.mockito.cglib.core.Local;

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
    private GlobalScore ownerScore;
    private GlobalScore occupantScore;
    private LocalDate registrationDate;

    public User(String name, String lastName, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        publications = new HashSet<>();
        reservations = new ArrayList<>();
        ownerScore = new GlobalScore();
        occupantScore = new GlobalScore();
        registrationDate = LocalDate.now();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
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
        ownerScore.clear();
        Set<Reservation> reservations = getOwnerFinalizedReservations();
        reservations.forEach(reservation -> {
        reservation.getOwnerScore().getScoreValues().forEach(scoreValue -> {
            addToPartialScores(ownerScore, scoreValue);
        });
        });
        calculateScoreAveragePerCategory(ownerScore, reservations.size());
        return ownerScore;
    }

    public GlobalScore getScoreAsOccupant() {
        occupantScore.clear();
        Set<Reservation> reservations = this.reservations.stream().filter(reservation -> reservation.isFinalized()).collect(Collectors.toSet());

        reservations.stream().forEach(reservation -> {
            reservation.getOccupantScore().getScoreValues().forEach(scoreValue -> {
                addToPartialScores(occupantScore, scoreValue);
            });
        });
        calculateScoreAveragePerCategory(occupantScore, reservations.size());
        return occupantScore;
    }

    private Set<Reservation> getOwnerFinalizedReservations() {
        Set<Reservation> reservations = new HashSet<>();
        publications.stream().forEach(publication -> {
            publication.getFinalizedReservations().forEach(reservation -> reservations.add(reservation));
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Long getPropertyReservationsCount(Property property) {
        return publications.stream().filter(publication -> publication.getProperty().equals(property)).count();
    }
}
