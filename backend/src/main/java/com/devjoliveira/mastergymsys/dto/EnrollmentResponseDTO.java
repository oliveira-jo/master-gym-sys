package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Enrollment;

public record EnrollmentResponseDTO(
    Long id,
    String enrollmentDate,
    Integer dueDay,
    String closingDate,
    StudentRequestDTO student) {

  public EnrollmentResponseDTO(Enrollment enrollment) {
    this(
        enrollment.getId(),
        enrollment.getEnrollmentDate().toString(),
        enrollment.getDueDay(),
        enrollment.getClosingDate() != null ? enrollment.getClosingDate().toString() : null,
        enrollment.getStudent() != null ? new StudentRequestDTO(enrollment.getStudent()) : null);
  }

}
