package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Modality;
import com.devjoliveira.mastergymsys.dto.ModalityResponseDTO;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

  Optional<ModalityResponseDTO> findByNameContainingIgnoreCase(String name);

}
