package br.com.jamesmayke.plantrip.modules.trip.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import br.com.jamesmayke.plantrip.modules.trip.dto.TripRequestPayload;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trips")
public class Trip {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String destination;

    @Column(name="starts_at", nullable=false)
    private LocalDateTime startsAt;

    @Column(name="ends_at", nullable=false)
    private LocalDateTime endsAt;

    @Column(name="is_confirmed", nullable=false)
    private Boolean isConfirmed;

    @Column(name="owner_name", nullable=false)
    private String ownerName;

    @Column(name="owner_email", nullable=false)
    private String ownerEmail;

    public UUID getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public Trip(TripRequestPayload data) {
        this.destination = data.destination();
        this.startsAt = LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.isConfirmed = false;
        this.ownerName = data.owner_name();
        this.ownerEmail = data.owner_email();
    }
    
}
