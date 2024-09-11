package br.com.jamesmayke.plantrip.modules.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jamesmayke.plantrip.modules.trip.dto.TripRequestPayload;
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
}
