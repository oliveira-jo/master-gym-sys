package com.devjoliveira.mastergymsys.dto;

import java.time.LocalDate;

import com.devjoliveira.mastergymsys.domain.EnrollmentModality;

import jakarta.validation.constraints.NotNull;

public record EnrollmentModalityRequestDTO(
    @NotNull(message = "Modality Id is required") Long modalityId,
    @NotNull(message = "Graduation Id is required") Long graduationId,
    @NotNull(message = "Subscription Id is required") Long subscriptionId,
    @NotNull(message = "Star Date is required") LocalDate startDate,
    @NotNull(message = "End Date is required") LocalDate endDate) {
  public EnrollmentModalityRequestDTO(EnrollmentModality enrollmentModality) {
    this(enrollmentModality.getModality().getId(),
        enrollmentModality.getGraduation().getId(),
        enrollmentModality.getSubscription().getId(),
        enrollmentModality.getStartDate(),
        enrollmentModality.getEndDate());
  }
}
