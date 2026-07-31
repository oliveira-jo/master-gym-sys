package com.devjoliveira.mastergymsys.repositoty;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.projection.EnrollmentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.ExpiringEnrollmentProjection;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.NewStudentsByMonthProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.PaymentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByModalityProjection;

public interface ReportRepository extends Repository<Payment, Long> {

  @Query(value = """
          SELECT
            TO_CHAR(payment_date, 'YYYY-MM') AS month,
            SUM(COALESCE(payment_amount, amount)) AS total
          FROM payments
          WHERE status = 'PAID'
            AND payment_date IS NOT NULL
          GROUP BY TO_CHAR(payment_date, 'YYYY-MM')
          ORDER BY month
      """, nativeQuery = true)
  List<MonthlyBillingProjection> monthlyBilling();

  @Query(value = """
        SELECT
          city,
          count(*) AS quantity
        FROM students
        GROUP BY city
        ORDER BY quantity desc
      """, nativeQuery = true)
  List<StudentsByCityProjection> studentsByCity();

  @Query(value = """
         SELECT
          en.id as enrollmentId,
          s.name as studentName,
          p.due_date as dueDate,
          p.amount
        FROM payments p
        JOIN enrollments en ON en.id = p.enrollment_id
        JOIN students s ON s.id = en.student_id
        WHERE p.status = 'OPEN'
        ORDER BY p.due_date ASC
      """, nativeQuery = true)
  List<OutstandingInvoicesProjection> outstandingInvoices();

  @Query(value = """
          SELECT
            m.name AS modalityName,
            COUNT(DISTINCT e.student_id) AS quantity
          FROM enrollment_modalities em
          JOIN enrollments e
            ON e.id = em.enrollment_id
          JOIN modalities m
            ON m.id = em.modality_id
          WHERE e.status = 'ACTIVE'
          GROUP BY m.id, m.name
          ORDER BY quantity DESC
      """, nativeQuery = true)
  List<StudentsByModalityProjection> studentsByModality();

  @Query("""
          SELECT
            CAST(e.status AS string) AS status,
            COUNT(e.id) AS quantity
          FROM Enrollment e
          GROUP BY e.status
          ORDER BY COUNT(e.id) DESC
      """)
  List<EnrollmentsByStatusProjection> enrollmentsByStatus();

  @Query("""
        SELECT
          CAST(p.status AS string) AS status,
          COUNT(p.id) AS quantity,
          COALESCE(SUM(p.amount), 0) AS total
        FROM Payment p
        GROUP BY p.status
        ORDER BY COUNT(p.id) DESC
      """)
  List<PaymentsByStatusProjection> paymentsByStatus();

  @Query(value = """
          SELECT
            TO_CHAR(
              created_at,
              'YYYY-MM'
            ) AS month,
            COUNT(*) AS quantity
          FROM students
          GROUP BY
            TO_CHAR(
              created_at,
              'YYYY-MM'
            )
          ORDER BY month
      """, nativeQuery = true)
  List<NewStudentsByMonthProjection> newStudentsByMonth();

  @Query("""
          SELECT
            e.id AS enrollmentId,
            e.student.name AS studentName,
            e.closingDate AS closingDate
          FROM Enrollment e
          WHERE e.status = 'ACTIVE'
            AND e.closingDate IS NOT NULL
            AND e.closingDate
                BETWEEN :startDate
                AND :endDate
          ORDER BY e.closingDate ASC
      """)
  List<ExpiringEnrollmentProjection> expiringEnrollments(@Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);

}
