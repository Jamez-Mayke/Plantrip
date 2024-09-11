package br.com.jamesmayke.plantrip.modules.participant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamesmayke.plantrip.modules.participant.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    
}
