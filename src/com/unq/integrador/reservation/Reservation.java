package com.unq.integrador.reservation;

import com.unq.integrador.publication.PaymentOption;
import com.unq.integrador.user.User;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.score.reviewer.OccupantScore;
import com.unq.integrador.score.reviewer.OwnerScore;
import com.unq.integrador.score.reviewer.PropertyScore;

import java.time.LocalDate;

public class Reservation {

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
    private PaymentOption paymentOption;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public OwnerScore getOwnerScore() {
        return ownerScore;
    }

    public void setOwnerScore(OwnerScore ownerScore) throws CanNotRankNonFinalizedReservationException {
        throwCouldNotRankNonFinalizedReservationExceptionIfNecessary();
        this.ownerScore = ownerScore;
    }

    public OccupantScore getOccupantScore() {
        return occupantScore;
    }

    public void setOccupantScore(OccupantScore occupantScore) throws CanNotRankNonFinalizedReservationException {
        throwCouldNotRankNonFinalizedReservationExceptionIfNecessary();
        this.occupantScore = occupantScore;
    }

    public PropertyScore getPropertyScore() {
        return propertyScore;
    }

    public void setPropertyScore(PropertyScore propertyScore) throws CanNotRankNonFinalizedReservationException {
        throwCouldNotRankNonFinalizedReservationExceptionIfNecessary();
        this.propertyScore = propertyScore;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void accept() {
        status.accept();
    }

    public void reject() {
        status.reject();
    }

    public void cancel() {
        status.cancel();
    }

    public void pending() {
        status.pending();
    }

    public void finalize() { status.finalize(); }

    public Boolean isAccepted() {
        return status.equals(acceptedStatus);
    }

    public Boolean isFinalized() {
        return status.equals(finalizedStatus);
    }

    private void throwCouldNotRankNonFinalizedReservationExceptionIfNecessary() throws CanNotRankNonFinalizedReservationException {
        if (!isFinalized()) {
            throw new CanNotRankNonFinalizedReservationException("Could not rank a non finalized reservation");
        }
    }
}
