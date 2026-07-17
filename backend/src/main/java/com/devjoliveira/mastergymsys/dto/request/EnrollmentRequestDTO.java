package com.devjoliveira.mastergymsys.dto.request;

import java.util.List;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO(

        @NotNull(message = "The Enrollment Date is required") LocalDate enrollmentDate,

        @NotNull(message = "Due day is required") Integer dueDay,

        LocalDate closingDate,

        @NotNull(message = "StudentId is required") Long studentId,

        @NotNull(message = "Enrollment modalities is required") List<EnrollmentModalityRequestDTO> enrollmentModalities

) {

}
