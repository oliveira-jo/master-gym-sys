package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Modality;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

  Optional<Modality> findByNameContainingIgnoreCase(String name);

}
