package com.devjoliveira.mastergymsys.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;

public interface ReportRepository extends Repository<Payment, Long> {

  @Query(value = """
        SELECT
          TO_CHAR(due_date, 'YYYY-MM') AS month,
          SUM(amount) AS total
        FROM payments
        WHERE status = 'PAID'
        GROUP BY TO_CHAR(due_date, 'YYYY-MM')
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
          en.id as subscriptionId,
          s.name as studentNome,
          p.due_date as dueDate,
          p.amount
        FROM payments p
        JOIN enrollments en ON en.id = p.enrollment_id
        JOIN students s ON s.id = en.student_id
        WHERE p.status = 'OPEN'
        ORDER BY p.due_date desc
      """, nativeQuery = true)
  List<OutstandingInvoicesProjection> outstandingInvoices();
}
