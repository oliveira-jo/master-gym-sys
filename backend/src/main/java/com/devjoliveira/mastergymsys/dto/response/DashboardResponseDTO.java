package com.devjoliveira.mastergymsys.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record DashboardResponseDTO(

        Long totalStudents,

        Long activeEnrollments,

        BigDecimal monthlyBilling,

        BigDecimal openAmount,

        BigDecimal overdueAmount,

        Long overduePayments,

        List<MonthlyBillingDTO> monthlyBillingHistory

) {
}