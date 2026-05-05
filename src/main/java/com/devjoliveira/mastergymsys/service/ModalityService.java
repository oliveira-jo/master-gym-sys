package com.devjoliveira.mastergymsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Modality;
import com.devjoliveira.mastergymsys.dto.ModalityRequestDTO;
import com.devjoliveira.mastergymsys.dto.ModalityResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.ModalityRepository;

@Service
public class ModalityService {

  private final ModalityRepository modalityRepository;

  public ModalityService(ModalityRepository modalityRepository) {
    this.modalityRepository = modalityRepository;
  }

  @Transactional(readOnly = true)
  public Page<ModalityResponseDTO> findAll(Pageable pageable) {
    return modalityRepository.findAll(pageable).map(ModalityResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public ModalityResponseDTO findById(Long id) {
    return modalityRepository.findById(id)
        .map(ModalityResponseDTO::new)
        .orElseThrow(() -> new RuntimeException("Modality not found with id: " + id));
  }

  @Transactional(readOnly = true)
  public ModalityResponseDTO findByName(String name) {
    return modalityRepository.findByNameContainingIgnoreCase(name)
        .orElseThrow(() -> new RuntimeException("Modality not found with name: " + name));
  }

  @Transactional
  public ModalityResponseDTO save(ModalityRequestDTO modalityRequestDTO) {

    Modality modality = new Modality();
    modality.setName(modalityRequestDTO.name());

    Modality fromDB = modalityRepository.save(modality);
    return new ModalityResponseDTO(fromDB);

  }

  @Transactional
  public ModalityResponseDTO update(Long id, ModalityRequestDTO modalityRequestDTO) {
    Modality fromDB = modalityRepository.getReferenceById(id);

    if (fromDB == null) {
      throw new RuntimeException("Modality not found with id: " + id);
    }

    fromDB.setName(modalityRequestDTO.name());

    return new ModalityResponseDTO(
        modalityRepository.save(fromDB));
  }

  @Transactional
  public void deleteById(Long id) {
    Modality fromDB = modalityRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Modality not found with id: " + id));

    try {
      modalityRepository.delete(fromDB);
    } catch (Exception e) {
      throw new RuntimeException("Error deleting modality with id: " + id, e);
    }
  }

}
