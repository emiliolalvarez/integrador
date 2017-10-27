package com.unq.integrador.reservation;

import com.unq.integrador.User;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.notifiaction.ReservationRequestNotification;
import com.unq.integrador.score.OwnerScore;
import com.unq.integrador.score.PropertyScore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OccupantReservationService {

    private ReservationService reservationService;

    public OccupantReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public List<Reservation> getOccupantReservations(User user) {
        return reservationService.getAll().stream().filter(reservation -> reservation.getOccupant().equals(user))
                .collect(Collectors.toList());
    }

    public List<Reservation> getFutureReservations(User user) {
        return reservationService.getAll().stream().filter(reservation -> reservation.getStartDate()
                .isAfter(LocalDate.now())).collect(Collectors.toList());
    }

    public List<Reservation> getCityReservations(User user, String city) {
        return reservationService.getAll().stream().filter(reservation -> reservation.getOccupant().equals(user)
                && reservation.getPublication().getCity().equals(city)).collect(Collectors.toList());
    }

    public Reservation requestReservation(Publication publication, User occupant, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = reservationService.addReservation(occupant, publication, startDate, endDate);
        (new ReservationRequestNotification(reservation)).send();
        return reservation;
    }

    public Set<String> getReservationsCities(User occupant) {
        Set<String> cities = new HashSet<>();
        reservationService.getAll().stream().filter(reservation -> reservation.getOccupant().equals(occupant))
                .map(reservation -> cities.add(reservation.getPublication().getCity()));
        return cities;
    }

    public void scoreProperty(Reservation reservation, PropertyScore score) {
        reservation.setPropertyScore(score);
    }

    public void scoreOwner(Reservation reservation, OwnerScore score) {
        reservation.setOwnerScore(score);
    }
}
