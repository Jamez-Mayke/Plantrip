package br.com.jamesmayke.plantrip.modules.activity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamesmayke.plantrip.modules.activity.dto.ActivityListResponse;
import br.com.jamesmayke.plantrip.modules.activity.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<ActivityListResponse> findByTripId(UUID id);
}
