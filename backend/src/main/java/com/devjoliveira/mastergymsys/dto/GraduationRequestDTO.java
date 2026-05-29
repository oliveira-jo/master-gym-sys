package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Graduation;

import jakarta.validation.constraints.NotBlank;

public record GraduationRequestDTO(
    @NotBlank(message = "Name is required") String name,
    @NotBlank(message = "ModalityId is required") String modalityId) {

  public GraduationRequestDTO(Graduation Graduation) {
    this(
        Graduation.getName(),
        Graduation.getModality() != null ? Graduation.getModality().getId().toString() : null);
  }

}
