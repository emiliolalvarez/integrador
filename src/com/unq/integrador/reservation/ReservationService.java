package com.unq.integrador.reservation;

import com.unq.integrador.User;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.PublicationStatus;
import com.unq.integrador.score.OccupantScore;
import com.unq.integrador.score.OwnerScore;
import com.unq.integrador.score.PropertyScore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    List<Reservation> reservations = new ArrayList<>();

    public Reservation addReservation(User requester, Publication publication, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = new Reservation(requester, publication, startDate, endDate);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getByStatus(Status status) {
        return reservations.stream().filter(request -> request.getStatus().equals(status)).collect(Collectors.toList());
    }

    public List<Reservation> getAll() {
        return reservations;
    }

    public void score(Reservation reservation, OccupantScore score) {
        reservation.setOccupantScore(score);
    }

    public void score(Reservation reservation, OwnerScore score) {
        reservation.setOwnerScore(score);
    }

    public void score(Reservation reservation, PropertyScore score) {
        reservation.setPropertyScore(score);
    }


    public PublicationStatus getPublicationStatusForDaterRange(Publication publication, LocalDate startDate, LocalDate endDate) {
        Long count = getAll().stream()
                .filter(reservation -> reservation.getPublication().equals(publication))
                .filter(reservation ->
                        (startDate.isAfter(reservation.getStartDate()) && startDate.isBefore(reservation.getEndDate()))
                                || (endDate.isAfter(reservation.getStartDate()) && endDate.isBefore(reservation.getEndDate()))
                                || ((endDate.isEqual(reservation.getStartDate()) || endDate.isEqual(reservation.getEndDate())))
                                || ((startDate.isEqual(reservation.getStartDate()) || startDate.isEqual(reservation.getEndDate())))
                ).count();
        return count == 0 ? PublicationStatus.AVAILABLE : PublicationStatus.RESERVED;
    }
}
