package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Graduation;

public interface GraduationRepository extends JpaRepository<Graduation, Long> {

  Optional<Graduation> findByNameContainingIgnoreCase(String name);

}
