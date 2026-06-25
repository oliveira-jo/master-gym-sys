package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Student;

import jakarta.validation.constraints.NotBlank;

public record ModalityRequestDTO(

    @NotBlank(message = "Name is required") String name) {

  // public ModalityRequestDTO(Student student) {
  // this(
  // student.getName());
  // }

}