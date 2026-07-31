package com.devjoliveira.mastergymsys.projection;

import java.time.LocalDate;

public interface ExpiringEnrollmentProjection {
  Long getEnrollmentId();

  String getStudentName();

  LocalDate getClosingDate();
}