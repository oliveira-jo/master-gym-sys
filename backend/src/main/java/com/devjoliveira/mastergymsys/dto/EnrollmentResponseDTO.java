package com.devjoliveira.mastergymsys.dto;

import java.util.List;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.EnrollmentModality;

public record EnrollmentResponseDTO(
    Long id,
    String enrollmentDate,
    Integer dueDay,
    String closingDate,
    StudentRequestDTO student,
    List<EnrollmentModalityResponseDTO> enrollmentModalityies) {

  public EnrollmentResponseDTO(Enrollment enrollment) {
    this(
        enrollment.getId(),
        enrollment.getEnrollmentDate().toString(),
        enrollment.getDueDay(),
        enrollment.getClosingDate() != null ? enrollment.getClosingDate().toString() : null,
        enrollment.getStudent() != null ? new StudentRequestDTO(enrollment.getStudent()) : null,
        enrollment.getEnrollmentModalities() != null
            ? enrollment.getEnrollmentModalities().stream().map(EnrollmentModalityResponseDTO::new).toList()
            : List.of());
  }

  public EnrollmentResponseDTO(Enrollment enrollment, List<EnrollmentModality> enrollmentModality) {
    this(
        enrollment.getId(),
        enrollment.getEnrollmentDate().toString(),
        enrollment.getDueDay(),
        enrollment.getClosingDate() != null ? enrollment.getClosingDate().toString() : null,
        enrollment.getStudent() != null ? new StudentRequestDTO(enrollment.getStudent()) : null,
        enrollmentModality != null
            ? enrollmentModality.stream().map(EnrollmentModalityResponseDTO::new).toList()
            : List.of());
  }

}
