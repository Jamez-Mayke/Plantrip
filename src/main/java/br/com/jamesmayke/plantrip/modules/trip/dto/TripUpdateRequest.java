package br.com.jamesmayke.plantrip.modules.trip.dto;

import java.time.LocalDateTime;

public record TripUpdateRequest(
    String destination,
    LocalDateTime starts_at,
    LocalDateTime ends_at
) {
    
}
