package com.devjoliveira.mastergymsys.dto.response;

import java.util.List;

public record PaymentHistoryResponseDTO(

    EnrollmentSummaryDTO enrollment,

    List<PaymentResponseDTO> payments

) {

}