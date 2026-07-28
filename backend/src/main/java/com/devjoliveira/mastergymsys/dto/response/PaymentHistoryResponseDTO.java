package com.devjoliveira.mastergymsys.dto.response;

import org.springframework.data.domain.Page;

public record PaymentHistoryResponseDTO(

        EnrollmentSummaryDTO enrollment,

        Page<PaymentResponseDTO> payments

) {

}