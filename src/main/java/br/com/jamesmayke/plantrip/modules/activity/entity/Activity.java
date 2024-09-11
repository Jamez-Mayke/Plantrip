package br.com.jamesmayke.plantrip.modules.activity.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import br.com.jamesmayke.plantrip.modules.activity.dto.ActivityRequestPayload;
import br.com.jamesmayke.plantrip.modules.trip.entity.Trip;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="activities")
public class Activity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String title;

    @Column(name="occurs_at", nullable=false)
    private LocalDateTime occursAt;

    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    private Trip trip;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getOccursAt() {
        return occursAt;
    }

    public void setOccursAt(LocalDateTime occursAt) {
        this.occursAt = occursAt;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Activity() {}

    public Activity(ActivityRequestPayload payload, Trip trip) {
        this.title = payload.title();
        this.occursAt = LocalDateTime.parse(payload.occurs_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.trip = trip;
    }
}
