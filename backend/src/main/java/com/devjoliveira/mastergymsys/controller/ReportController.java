package com.devjoliveira.mastergymsys.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.mastergymsys.doc.ReportControllerDoc;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;
import com.devjoliveira.mastergymsys.repositoty.ReportRepository;

@RestController
@RequestMapping("/reports")
public class ReportController implements ReportControllerDoc {

  private final ReportRepository reportRepository;

  public ReportController(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ATTENDANT')")
  @GetMapping("/monthlyBilling")
  public ResponseEntity<List<MonthlyBillingProjection>> monthlyBilling() {
    return ResponseEntity.ok().body(reportRepository.monthlyBilling());
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ATTENDANT')")
  @GetMapping("/studentsByCity")
  public ResponseEntity<List<StudentsByCityProjection>> studentsByCity() {
    return ResponseEntity.ok().body(reportRepository.studentsByCity());
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ATTENDANT')")
  @GetMapping("/outstandingInvoices")
  public ResponseEntity<List<OutstandingInvoicesProjection>> outstandingInvoices() {
    return ResponseEntity.ok().body(reportRepository.outstandingInvoices());
  }

}
