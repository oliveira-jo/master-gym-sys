package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.Graduation;

public record GraduationResponseDTO(
    Long id,
    String name,
    Long modalityId,
    String modalityName) {

  public GraduationResponseDTO(Graduation graduation) {
    this(
        graduation.getId(),
        graduation.getName(),
        graduation.getModality().getId(),
        graduation.getModality().getName());
  }

}
