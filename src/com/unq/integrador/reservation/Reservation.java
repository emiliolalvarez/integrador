package com.unq.integrador.reservation;

import com.unq.integrador.user.User;
import com.unq.integrador.mail.MailServer;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.score.OccupantScore;
import com.unq.integrador.score.OwnerScore;
import com.unq.integrador.score.PropertyScore;

import java.time.LocalDate;

public class Reservation implements MailServer {

    private AcceptedStatus acceptedStatus;
    private CancelledStatus cancelledStatus;
    private PendingStatus pendingStatus;
    private RejectedStatus rejectedStatus;
    private FinalizedStatus finalizedStatus;

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
        acceptedStatus = new AcceptedStatus(this);
        cancelledStatus = new CancelledStatus(this);
        pendingStatus = new PendingStatus(this);
        rejectedStatus = new RejectedStatus(this);
        finalizedStatus = new FinalizedStatus(this);
        status = pendingStatus;
    }

    public AcceptedStatus getAcceptedStatus() {
        return acceptedStatus;
    }

    public CancelledStatus getCancelledStatus() {
        return cancelledStatus;
    }

    public PendingStatus getPendingStatus() {
        return pendingStatus;
    }

    public RejectedStatus getRejectedStatus() {
        return rejectedStatus;
    }

    public FinalizedStatus getFinalizedStatus() {
        return finalizedStatus;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
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

    @Override
    public void sendMail(String destinationAddress, String subject, String body) {
        System.out.println("Sending email to " + destinationAddress);
        System.out.println("Subject: " + subject);
        System.out.println("body");
    }
}
