package br.com.jamesmayke.plantrip.modules.participant.dto;

public record ParticipantResponsePayload(
    String name,
    String email,
    Boolean is_confirmed
) {
    
}
