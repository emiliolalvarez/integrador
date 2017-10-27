package com.unq.integrador.reservation;

import com.unq.integrador.User;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.score.OccupantScore;
import com.unq.integrador.score.OwnerScore;
import com.unq.integrador.score.PropertyScore;

import java.time.LocalDate;

public class Reservation {

    private Status status;
    private Publication publication;
    private User occupant;
    private LocalDate startDate;
    private LocalDate endDate;
    private OwnerScore ownerScore;
    private OccupantScore occupantScore;
    private PropertyScore propertyScore;

    public Reservation(User occupant, Publication publication, LocalDate startDate, LocalDate endDate) {
        this.publication = publication;
        this.occupant = occupant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.PENDING;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getOccupant() {
        return occupant;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setOccupant(User occupant) {
        this.occupant = occupant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public OwnerScore getOwnerScore() {
        return ownerScore;
    }

    public void setOwnerScore(OwnerScore ownerScore) {
        this.ownerScore = ownerScore;
    }

    public OccupantScore getOccupantScore() {
        return occupantScore;
    }

    public void setOccupantScore(OccupantScore occupantScore) {
        this.occupantScore = occupantScore;
    }

    public PropertyScore getPropertyScore() {
        return propertyScore;
    }

    public void setPropertyScore(PropertyScore propertyScore) {
        this.propertyScore = propertyScore;
    }
}
