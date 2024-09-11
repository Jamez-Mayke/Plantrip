package br.com.jamesmayke.plantrip.modules.trip.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jamesmayke.plantrip.modules.trip.dto.TripRequestPayload;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripResponseData;
import br.com.jamesmayke.plantrip.modules.trip.dto.TripResponsePayload;
import br.com.jamesmayke.plantrip.modules.trip.entity.Trip;
import br.com.jamesmayke.plantrip.modules.trip.repository.TripRepository;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripRepository repository;
    
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

}
