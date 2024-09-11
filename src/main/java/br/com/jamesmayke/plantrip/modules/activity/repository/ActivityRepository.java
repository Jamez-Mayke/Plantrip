package br.com.jamesmayke.plantrip.modules.activity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamesmayke.plantrip.modules.activity.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    
}
