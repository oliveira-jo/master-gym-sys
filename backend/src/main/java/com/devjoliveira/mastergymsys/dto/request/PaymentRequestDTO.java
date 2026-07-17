package com.devjoliveira.mastergymsys.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record PaymentRequestDTO(

    @NotNull(message = "Due day is required") Integer dueDate,

    @NotNull(message = "Amount is required") @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero") BigDecimal amount,

    @NotNull(message = "Payment Amount is required") @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero") BigDecimal paymentAmount,

    @NotNull(message = "The Payment Date is required") LocalDateTime paymentDate,

    LocalDate canceledDate,

    String observation,

    @NotNull(message = "Enrollment id is required") Long enrollmentId

) {

}
