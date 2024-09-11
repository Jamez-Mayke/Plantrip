package br.com.jamesmayke.plantrip.modules.activity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityListResponse(
    UUID id,
    String title,
    LocalDateTime occursAt
) {
    
}
