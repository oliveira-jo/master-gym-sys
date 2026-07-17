package com.devjoliveira.mastergymsys.repositoty;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  List<Payment> findByStatus(StatusPayment status);

  List<Payment> findByEnrollmentId(Long enrollmentId);

  List<Payment> findByDueDate(LocalDate dueDate);

  // Searches only for outstanding payments that are past due
  List<Payment> findByDueDateBeforeAndStatus(LocalDate date, StatusPayment status);

}
