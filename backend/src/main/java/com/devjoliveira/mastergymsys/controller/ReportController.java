package com.devjoliveira.mastergymsys.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.mastergymsys.doc.ReportControllerDoc;
import com.devjoliveira.mastergymsys.dto.response.DashboardResponseDTO;
import com.devjoliveira.mastergymsys.projection.EnrollmentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.ExpiringEnrollmentProjection;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.NewStudentsByMonthProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.PaymentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByModalityProjection;
import com.devjoliveira.mastergymsys.repositoty.ReportRepository;
import com.devjoliveira.mastergymsys.service.ReportService;

@RestController
@RequestMapping("/reports")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
public class ReportController implements ReportControllerDoc {

  private final ReportRepository reportRepository;
  private final ReportService reportService;

  public ReportController(ReportRepository reportRepository, ReportService reportService) {
    this.reportRepository = reportRepository;
    this.reportService = reportService;
  }

  @GetMapping("/dashboard")
  public ResponseEntity<DashboardResponseDTO> getDashboard() {
    return ResponseEntity.ok(reportService.getDashboard());
  }

  @GetMapping("/monthly-billing")
  public ResponseEntity<List<MonthlyBillingProjection>> monthlyBilling() {
    return ResponseEntity.ok().body(reportRepository.monthlyBilling());
  }

  @GetMapping("/students-by-city")
  public ResponseEntity<List<StudentsByCityProjection>> studentsByCity() {
    return ResponseEntity.ok().body(reportRepository.studentsByCity());
  }

  @GetMapping("/outstanding-invoices")
  public ResponseEntity<List<OutstandingInvoicesProjection>> outstandingInvoices() {
    return ResponseEntity.ok().body(reportRepository.outstandingInvoices());
  }

  @GetMapping("/students-by-modality")
  public ResponseEntity<List<StudentsByModalityProjection>> studentsByModality() {
    return ResponseEntity.ok().body(reportRepository.studentsByModality());
  }

  @GetMapping("/enrollments-by-status")
  public ResponseEntity<List<EnrollmentsByStatusProjection>> enrollmentsByStatus() {
    return ResponseEntity.ok().body(reportRepository.enrollmentsByStatus());
  }

  @GetMapping("/payments-by-status")
  public ResponseEntity<List<PaymentsByStatusProjection>> paymentsByStatus() {
    return ResponseEntity.ok().body(reportRepository.paymentsByStatus());
  }

  @GetMapping("/new-students-by-month")
  public ResponseEntity<List<NewStudentsByMonthProjection>> newStudentsByMonth() {
    return ResponseEntity.ok().body(reportRepository.newStudentsByMonth());
  }

  @GetMapping("/expiring-enrollments")
  public ResponseEntity<List<ExpiringEnrollmentProjection>> expiringEnrollments() {
    LocalDate today = LocalDate.now();
    LocalDate limit = today.plusDays(30);
    return ResponseEntity.ok().body(reportRepository.expiringEnrollments(today, limit));
  }

}
