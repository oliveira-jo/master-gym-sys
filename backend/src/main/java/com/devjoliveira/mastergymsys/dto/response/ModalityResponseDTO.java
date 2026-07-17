package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.Modality;

public record ModalityResponseDTO(
    Long id,
    String name) {

  public ModalityResponseDTO(Modality modality) {
    this(
        modality.getId(),
        modality.getName());
  }

}