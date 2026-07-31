package com.devjoliveira.mastergymsys.repositoty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;

@SuppressWarnings("null")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

  @Query("""
          SELECT p
          FROM Payment p
          WHERE p.enrollment.id = :enrollmentId
          ORDER BY p.dueDate DESC
      """)
  List<Payment> findHistory(Long enrollmentId);

  Page<Payment> findAll(Pageable pageable);

  Page<Payment> findByStatus(Pageable pageable, StatusPayment status);

  Page<Payment> findByEnrollmentId(Pageable pageable, Long enrollmentId);

  List<Payment> findByDueDate(LocalDate dueDate);

  // Searches only for outstanding payments that are past due
  List<Payment> findByDueDateBeforeAndStatus(LocalDate date, StatusPayment status);

  boolean existsByEnrollmentAndStatus(Enrollment enrollment, StatusPayment status);

  // For Dashboard
  @Query("""
        SELECT COALESCE(SUM(p.paymentAmount), 0)
        FROM Payment p
        WHERE p.status = :status
      """)
  BigDecimal sumAmountByStatus(@Param("status") StatusPayment status);

  @Query("""
          SELECT COALESCE(SUM(p.amount), 0)
          FROM Payment p
          WHERE p.status = :status
            AND p.dueDate < :currentDate
      """)
  BigDecimal sumOverdueAmount(
      @Param("status") StatusPayment status,
      @Param("currentDate") LocalDate currentDate);

  long countByStatus(StatusPayment status);

  @Query("""
          SELECT COALESCE(SUM(p.paymentAmount), 0)
          FROM Payment p
          WHERE p.status = :status
            AND p.paymentDate >= :startDate
            AND p.paymentDate < :endDate
      """)
  BigDecimal sumMonthlyBilling(@Param("status") StatusPayment status,
      @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
