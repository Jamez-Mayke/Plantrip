package br.com.jamesmayke.plantrip.modules.link.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamesmayke.plantrip.modules.link.entity.Link;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    
}
