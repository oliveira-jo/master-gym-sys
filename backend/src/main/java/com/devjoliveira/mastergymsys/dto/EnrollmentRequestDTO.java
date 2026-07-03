package com.devjoliveira.mastergymsys.dto;

import java.util.List;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO(

        // @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "The Enrollment Date is required") LocalDate enrollmentDate,

        @NotNull(message = "Due day is required") Integer dueDay,

        // @JsonFormat(pattern = "dd/MM/yyyy")
        // @NotBlank(message = "The Enrollment Date is required")
        LocalDate closingDate,

        @NotNull(message = "StudentId is required") Long studentId,

        @NotNull(message = "Enrollment modalities is required") List<EnrollmentModalityRequestDTO> enrollmentModalityies

) {

}
