package com.devjoliveira.mastergymsys.repositoty;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  @Query("""
          SELECT p
          FROM Payment p
          WHERE p.enrollment.id = :enrollmentId
          ORDER BY p.dueDate DESC
      """)
  List<Payment> findHistory(Long enrollmentId);

  @NonNull
  Page<Payment> findAll(Pageable pageable);

  Page<Payment> findByStatus(Pageable pageable, StatusPayment status);

  Page<Payment> findByEnrollmentId(Pageable pageable, Long enrollmentId);

  List<Payment> findByDueDate(LocalDate dueDate);

  // Searches only for outstanding payments that are past due
  List<Payment> findByDueDateBeforeAndStatus(LocalDate date, StatusPayment status);

}
