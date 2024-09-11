package br.com.jamesmayke.plantrip.modules.trip.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamesmayke.plantrip.modules.trip.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}
