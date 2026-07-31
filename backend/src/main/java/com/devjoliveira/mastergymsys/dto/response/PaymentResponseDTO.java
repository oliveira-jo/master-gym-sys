package com.devjoliveira.mastergymsys.dto.response;

import java.math.BigDecimal;

import com.devjoliveira.mastergymsys.domain.Payment;

public record PaymentResponseDTO(

    Long id,
    String dueDate,
    BigDecimal amount,
    BigDecimal paymentAmount,
    String paymentDate,
    String canceledAt,
    String status,
    String observation,
    Long enrollmentId

) {

  public PaymentResponseDTO(Payment payment) {
    this(
        payment.getId(),
        payment.getDueDate() != null ? payment.getDueDate().toString() : "0000-00-00T00:00:00",
        payment.getAmount(),
        payment.getPaymentAmount(),
        payment.getPaymentDate() != null ? payment.getPaymentDate().toString() : "0000-00-00T00:00:00",
        payment.getCanceledAt() != null ? payment.getCanceledAt().toString() : "0000-00-00T00:00:00",
        payment.getStatus().toString(),
        payment.getObservation(),
        payment.getEnrollment().getId());
  }
}
