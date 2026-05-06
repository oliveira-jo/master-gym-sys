package com.devjoliveira.mastergymsys.dto;

import java.time.LocalDate;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO(

    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate enrollmentDate,
    @NotNull(message = "Due day is required") Integer dueDay,
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate closingDate,
    @NotBlank(message = "StudentId is required") String studentId) {

  public EnrollmentRequestDTO(Enrollment enrollment) {
    this(enrollment.getEnrollmentDate(),
        enrollment.getDueDay(),
        enrollment.getClosingDate(),
        enrollment.getStudent().getId().toString());
  }

}
