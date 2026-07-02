package com.devjoliveira.mastergymsys.dto;

import java.time.LocalDate;

import com.devjoliveira.mastergymsys.domain.EnrollmentModality;

public record EnrollmentModalityResponseDTO(
    Long id,
    ModalityResponseDTO modality,
    GraduationResponseDTO graduation,
    SubscriptionResponseDTO subscription,
    LocalDate startDate,
    LocalDate endDate) {
  public EnrollmentModalityResponseDTO(EnrollmentModality enrollmentModality) {
    this(enrollmentModality.getId(),
        new ModalityResponseDTO(enrollmentModality.getModality()),
        new GraduationResponseDTO(enrollmentModality.getGraduation()),
        new SubscriptionResponseDTO(enrollmentModality.getSubscription()),
        enrollmentModality.getStartDate(),
        enrollmentModality.getEndDate());
  }
}
