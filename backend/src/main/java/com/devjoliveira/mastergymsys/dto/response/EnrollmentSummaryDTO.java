package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.enums.StatusEnrollment;

public record EnrollmentSummaryDTO(

        Long id,

        String studentName,

        Integer dueDay,

        StatusEnrollment status

) {
}