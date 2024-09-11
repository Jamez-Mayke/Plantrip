package br.com.jamesmayke.plantrip.modules.trip.dto;

import java.time.LocalDateTime;

public record TripResponseData(
    String destination,
    LocalDateTime starts_at,
    LocalDateTime ends_at,
    Boolean is_confirmed,
    String owner_name,
    String owner_email
) {
    
}
