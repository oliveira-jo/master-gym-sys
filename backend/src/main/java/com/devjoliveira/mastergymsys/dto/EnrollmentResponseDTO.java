package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Enrollment;

public record EnrollmentResponseDTO(
    Long id,
    String enrollmentDate,
    Integer dueDay,
    String closingDate,
    Long studentId) {

  public EnrollmentResponseDTO(Enrollment enrollment) {
    this(
        enrollment.getId(),
        enrollment.getEnrollmentDate().toString(),
        enrollment.getDueDay(),
        enrollment.getClosingDate().toString(),
        enrollment.getStudent().getId());
  }

}
