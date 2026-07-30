package com.devjoliveira.mastergymsys.dto.response;

import java.math.BigDecimal;

public record MonthlyBillingDTO(
        String month,
        BigDecimal total) {
}