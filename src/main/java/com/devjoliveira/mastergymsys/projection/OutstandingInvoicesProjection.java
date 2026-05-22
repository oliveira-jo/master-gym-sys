package com.devjoliveira.mastergymsys.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OutstandingInvoicesProjection {

  Long getEnrollmentId();

  String getStudentName();

  LocalDate getDueDate();

  BigDecimal getValue();

}
