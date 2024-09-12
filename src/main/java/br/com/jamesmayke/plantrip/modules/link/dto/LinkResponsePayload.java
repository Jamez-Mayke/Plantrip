package br.com.jamesmayke.plantrip.modules.link.dto;

import java.util.UUID;

public record LinkResponsePayload(
    UUID id,
    String title,
    String link
) {
    
}
