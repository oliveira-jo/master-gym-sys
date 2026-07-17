package com.devjoliveira.mastergymsys.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionRequestDTO(
    @NotBlank(message = "Name is required") String name,
    @NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero") BigDecimal price,
    @NotNull(message = "ModalityId is required") Long modalityId) {

}
