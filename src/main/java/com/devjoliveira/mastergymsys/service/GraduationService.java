package com.devjoliveira.mastergymsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Graduation;
import com.devjoliveira.mastergymsys.domain.Modality;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.GraduationRequestDTO;
import com.devjoliveira.mastergymsys.dto.GraduationResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.GraduationRepository;
import com.devjoliveira.mastergymsys.repositoty.ModalityRepository;

@Service
public class GraduationService {

  private final GraduationRepository graduationRepository;
  private final ModalityRepository modalityRepository;

  public GraduationService(GraduationRepository graduationRepository, ModalityRepository modalityRepository) {
    this.graduationRepository = graduationRepository;
    this.modalityRepository = modalityRepository;
  }

  @Transactional(readOnly = true)
  public Page<GraduationResponseDTO> findAll(Pageable pageable) {
    return graduationRepository.findAll(pageable).map(GraduationResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public GraduationResponseDTO findById(Long id) {
    return new GraduationResponseDTO(searchById(id));
  }

  @Transactional(readOnly = true)
  public GraduationResponseDTO findByName(String name) {
    return graduationRepository.findByNameContainingIgnoreCase(name)
        .map(GraduationResponseDTO::new)
        .orElseThrow(() -> new BusinessException("Graduation not found with name: " + name));
  }

  @Transactional
  public GraduationResponseDTO save(GraduationRequestDTO graduationRequestDTO) {

    Long modalityId;
    try {
      modalityId = Long.valueOf(graduationRequestDTO.modalityId());
    } catch (NumberFormatException e) {
      throw new BusinessException("Invalid modality id: " + graduationRequestDTO.modalityId() + " : " + e);
    }

    Modality modalityFromDB = modalityRepository.findById(modalityId)
        .orElseThrow(() -> new BusinessException("Modality not found with id: " + modalityId));

    Graduation graduation = new Graduation();
    graduation.setName(graduationRequestDTO.name());
    graduation.setModality(modalityFromDB);

    Graduation fromDB = graduationRepository.save(graduation);
    return new GraduationResponseDTO(fromDB);

  }

  @Transactional
  public GraduationResponseDTO update(Long id, GraduationRequestDTO graduationRequestDTO) {

    Graduation fromDB = searchById(id);

    fromDB.setName(graduationRequestDTO.name());

    return new GraduationResponseDTO(
        graduationRepository.save(fromDB));
  }

  @Transactional
  public void deleteById(Long id) {
    Graduation fromDB = searchById(id);

    try {
      graduationRepository.delete(fromDB);
    } catch (Exception e) {
      throw new BusinessException("Error deleting graduation with id: " + id + ":" + e);
    }
  }

  private Graduation searchById(Long id) {
    return graduationRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Graduation not found with id: " + id));
  }

}
