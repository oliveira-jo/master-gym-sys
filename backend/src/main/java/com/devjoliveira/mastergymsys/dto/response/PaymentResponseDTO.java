package com.devjoliveira.mastergymsys.dto.response;

import java.math.BigDecimal;

import com.devjoliveira.mastergymsys.domain.Payment;

public record PaymentResponseDTO(

    Long id,
    String dueDate,
    BigDecimal amount,
    BigDecimal paymentAmount,
    String paymentDate,
    String canceledDate,
    String status,
    String observation,
    Long enrollmentId

) {

  public PaymentResponseDTO(Payment payment) {
    this(
        payment.getId(),
        payment.getDueDate().toString(),
        payment.getAmount(),
        payment.getPaymentAmount(),
        payment.getPaymentDate().toString(),
        payment.getCanceledDate().toString(),
        payment.getStatus().toString(),
        payment.getObservation(),
        payment.getEnrollment().getId());
  }
}
