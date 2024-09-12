package br.com.jamesmayke.plantrip.modules.trip.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jamesmayke.plantrip.modules.activity.dto.ActivityListResponse;
import br.com.jamesmayke.plantrip.modules.activity.dto.ActivityRequestPayload;
import br.com.jamesmayke.plantrip.modules.activity.dto.ActivityResponsePayload;
import br.com.jamesmayke.plantrip.modules.activity.entity.Activity;
import br.com.jamesmayke.plantrip.modules.activity.repository.ActivityRepository;
import br.com.jamesmayke.plantrip.modules.link.dto.LinkRequestPayload;
import br.com.jamesmayke.plantrip.modules.link.dto.LinkResponsePayload;
import br.com.jamesmayke.plantrip.modules.link.entity.Link;
import br.com.jamesmayke.plantrip.modules.link.repository.LinkRepository;
import br.com.jamesmayke.plantrip.modules.participant.dto.ParticipantRequestPayload;
import br.com.jamesmayke.plantrip.modules.participant.dto.ParticipantResponsePayload;
import br.com.jamesmayke.plantrip.modules.participant.entity.Participant;
import br.com.jamesmayke.plantrip.modules.participant.repository.ParticipantRepository;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripRequestPayload;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripResponseData;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripResponsePayload;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripUpdateRequest;
import br.com.jamesmayke.plantrip.modules.trip.entity.Trip;
import br.com.jamesmayke.plantrip.modules.trip.repository.TripRepository;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripRepository repository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private LinkRepository linkRepository;
    
    // Trips

    @PostMapping("/")
    public TripResponsePayload create(@RequestBody TripRequestPayload payload) {
        Trip trip = new Trip(payload);

        this.repository.save(trip);

        return new TripResponsePayload(trip.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponseData> getTripById(@PathVariable UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Trip tripRaw = trip.get();
        
            TripResponseData tripResponse = new TripResponseData(
                tripRaw.getDestination(), 
                tripRaw.getStartsAt(), 
                tripRaw.getEndsAt(), 
                tripRaw.getIsConfirmed(), 
                tripRaw.getOwnerName(), 
                tripRaw.getOwnerEmail());

            return ResponseEntity.ok(tripResponse);
        }

        return ResponseEntity.notFound().build();
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripUpdateRequest payload) {
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Trip tripRaw = trip.get();
            
            tripRaw.setDestination(payload.destination());
            tripRaw.setStartsAt(payload.starts_at());
            tripRaw.setEndsAt(payload.ends_at());

            this.repository.save(tripRaw);

            return ResponseEntity.ok(tripRaw);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirm")
    public void confirmTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Trip tripRaw = trip.get();
            tripRaw.setIsConfirmed(true);

            this.repository.save(tripRaw);
        }
    }

    // Participants

    @PostMapping("/{id}/invites")
    public ResponseEntity<Participant> inviteParticipants(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload ) {
        Optional<Trip> trip = this.repository.findById(id);

        if (trip.isPresent()) {
            Participant participant = new Participant(payload, trip.get());

            this.participantRepository.save(participant);

            return ResponseEntity.ok(participant);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tripId}/participants")
    public ResponseEntity<List<ParticipantResponsePayload>> getAllParticipants(@PathVariable UUID tripId) {
        Optional<Participant> participants = this.participantRepository.findByTripId(tripId);

        List<ParticipantResponsePayload> participantsRaw = participants.stream().map(
            participant -> new ParticipantResponsePayload(
                participant.getName(), 
                participant.getEmail(), 
                participant.getIsConfirmed())).toList();
        
        return ResponseEntity.ok(participantsRaw);
    }

    // Activities

    @PostMapping("/{tripId}/activities")
    public ResponseEntity<ActivityResponsePayload> createActivity(@PathVariable UUID tripId, @RequestBody ActivityRequestPayload payload) {
        Optional<Trip> trip = this.repository.findById(tripId);

        if (trip.isPresent()) {
            Activity activity = new Activity(payload, trip.get());

            this.activityRepository.save(activity);

            return ResponseEntity.ok(new ActivityResponsePayload(activity.getId()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tripId}/activities")
    public ResponseEntity<List<ActivityListResponse>> getAllActivities(@PathVariable UUID tripId) {
        Optional<Trip> trip = this.repository.findById(tripId);
        if (trip.isPresent()) {
            List<ActivityListResponse> allActivities = this.activityRepository.findByTripId(tripId);
            return ResponseEntity.ok(allActivities);
        }

        return ResponseEntity.notFound().build();
    }

    // Links

    @PostMapping("/{tripId}/links")
    public ResponseEntity<Link> createLink(@PathVariable UUID tripId, @RequestBody LinkRequestPayload payload) {
        Optional<Trip> trip = this.repository.findById(tripId);

        if (trip.isPresent()) {
            Link link = new Link(payload, trip.get());

            this.linkRepository.save(link);

            return ResponseEntity.ok(link);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tripId}/links")
    public ResponseEntity<List<LinkResponsePayload>> getAllLinks(@PathVariable UUID tripId) {
        Optional<Trip> trip = this.repository.findById(tripId);

        if (trip.isPresent()) {
            List<LinkResponsePayload> links = this.linkRepository.findByTripId(tripId);
            return ResponseEntity.ok(links);
        }

        return ResponseEntity.notFound().build();
    }

}
