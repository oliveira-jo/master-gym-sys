package com.devjoliveira.mastergymsys.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.enums.StatusEnrollment;
import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;
import com.devjoliveira.mastergymsys.dto.response.DashboardResponseDTO;
import com.devjoliveira.mastergymsys.dto.response.MonthlyBillingDTO;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.repositoty.EnrollmentRepository;
import com.devjoliveira.mastergymsys.repositoty.PaymentRepository;
import com.devjoliveira.mastergymsys.repositoty.ReportRepository;
import com.devjoliveira.mastergymsys.repositoty.StudentRepository;

@Service
public class ReportService {

  private final StudentRepository studentRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final PaymentRepository paymentRepository;
  private final ReportRepository reportRepository;

  public ReportService(
      StudentRepository studentRepository,
      EnrollmentRepository enrollmentRepository,
      PaymentRepository paymentRepository,
      ReportRepository reportRepository) {
    this.studentRepository = studentRepository;
    this.enrollmentRepository = enrollmentRepository;
    this.paymentRepository = paymentRepository;
    this.reportRepository = reportRepository;
  }

  @Transactional(readOnly = true)
  public DashboardResponseDTO getDashboard() {

    Long activeStudents = studentRepository.count();

    Long activeEnrollments = enrollmentRepository.countByStatus(StatusEnrollment.ACTIVE);

    BigDecimal openAmount = paymentRepository.sumAmountByStatus(StatusPayment.OPEN);

    BigDecimal overdueAmount = paymentRepository.sumOverdueAmount(StatusPayment.OPEN, LocalDate.now());

    Long overduePayments = paymentRepository.countByStatus(StatusPayment.OVERDUE);

    BigDecimal monthlyBilling = calculateMonthlyBilling();

    List<MonthlyBillingDTO> history = getMonthlyBillingHistory();

    return new DashboardResponseDTO(
        activeStudents,
        activeEnrollments,
        monthlyBilling,
        openAmount,
        overdueAmount,
        overduePayments,
        history);
  }

  private BigDecimal calculateMonthlyBilling() {
    LocalDate today = LocalDate.now();
    LocalDateTime startDate = today.withDayOfMonth(1).atStartOfDay();
    LocalDateTime endDate = startDate.plusMonths(1);
    return paymentRepository.sumMonthlyBilling(StatusPayment.PAID, startDate, endDate);
  }

  private List<MonthlyBillingDTO> getMonthlyBillingHistory() {
    return reportRepository.monthlyBilling().stream().map(this::toMonthlyBillingDTO).toList();
  }

  private MonthlyBillingDTO toMonthlyBillingDTO(MonthlyBillingProjection projection) {
    return new MonthlyBillingDTO(projection.getMonth(), projection.getTotal());
  }
}