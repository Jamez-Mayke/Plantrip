package br.com.jamesmayke.plantrip.modules.participant.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jamesmayke.plantrip.modules.participant.dto.ParticipantRequestPayload;
import br.com.jamesmayke.plantrip.modules.participant.entity.Participant;
import br.com.jamesmayke.plantrip.modules.participant.repository.ParticipantRepository;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipantInTrip(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.participantRepository.findById(id);
       

        if (participant.isPresent()) {
            Participant participantRaw = participant.get();
            participantRaw.setIsConfirmed(true);
            participantRaw.setName(payload.name());

            this.participantRepository.save(participantRaw);

            return ResponseEntity.ok(participantRaw);
        }

        return ResponseEntity.notFound().build();
    }
    
}
