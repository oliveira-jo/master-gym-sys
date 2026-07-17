package com.devjoliveira.mastergymsys.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ModalityRequestDTO(

        @NotBlank(message = "Name is required") String name) {

}