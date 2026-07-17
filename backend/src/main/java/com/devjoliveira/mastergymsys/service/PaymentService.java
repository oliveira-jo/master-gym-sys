package com.devjoliveira.mastergymsys.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.Payment;
import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.request.PaymentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.PaymentResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.EnrollmentRepository;
import com.devjoliveira.mastergymsys.repositoty.PaymentRepository;

@Service
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final EnrollmentRepository enrollmentRepository;

  public PaymentService(PaymentRepository paymentRepository, EnrollmentRepository enrollmentRepository) {
    this.paymentRepository = paymentRepository;
    this.enrollmentRepository = enrollmentRepository;
  }

  @Transactional
  public PaymentResponseDTO create(PaymentRequestDTO request) {

    Payment newPayment = new Payment();
    newPayment.setId(null);
    newPayment.setStatus(StatusPayment.OPEN);

    // Set due date
    LocalDate today = LocalDate.now();
    LocalDate newDate = LocalDate.of(today.getYear(), today.getMonth(), request.dueDate());
    newPayment.setDueDate(newDate);

    newPayment.setAmount(request.amount());
    newPayment.setPaymentAmount(request.paymentAmount());
    newPayment.setPaymentDate(request.paymentDate());
    newPayment.setCanceledDate(request.canceledDate());
    newPayment.setObservation(request.observation());

    // verify if exists enrollment id
    Enrollment erFromDB = enrollmentRepository.findById(request.enrollmentId()).orElseThrow(
        () -> new BusinessException("Enrollment not found with id: " + request.enrollmentId()));

    newPayment.setEnrollment(erFromDB);

    var fromDb = paymentRepository.save(newPayment);

    return new PaymentResponseDTO(fromDb);

  }

  @Transactional
  public void cancel(Long paymentId) {
    Payment payment = searchPaymentById(paymentId);

    if (payment.getStatus() == StatusPayment.PAID)
      throw new BusinessException("A paid payment cannot be canceled.");

    if (payment.getStatus() == StatusPayment.CANCELED)
      throw new BusinessException("Payment is already canceled.");

    payment.setStatus(StatusPayment.CANCELED);

    paymentRepository.save(payment);
  }

  @Transactional
  public void pay(Long paymentId) {
    Payment payment = searchPaymentById(paymentId);

    if (payment.getStatus() == StatusPayment.PAID)
      throw new BusinessException("Payment has already been paid.");

    if (payment.getStatus() == StatusPayment.CANCELED)
      throw new BusinessException("Canceled payments cannot be paid.");

    payment.setStatus(StatusPayment.PAID);
    payment.setPaymentDate(LocalDateTime.now());
    payment.setPaymentAmount(payment.getAmount());

    paymentRepository.save(payment);
  }

  @Transactional(readOnly = true)
  public Page<PaymentResponseDTO> findPaidPayments(Pageable pageable) {
    return paymentRepository.findByStatus(pageable, StatusPayment.PAID).map(PaymentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<PaymentResponseDTO> findOpenPayments(Pageable pageable) {
    return paymentRepository.findByStatus(pageable, StatusPayment.OPEN).map(PaymentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<PaymentResponseDTO> findOverduePayments(Pageable pageable) {
    return paymentRepository.findByStatus(pageable, StatusPayment.OVERDUE).map(PaymentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<PaymentResponseDTO> findByEnrollmentId(Pageable pageable, Long enrollmentId) {
    return paymentRepository.findByEnrollmentId(pageable, enrollmentId).map(PaymentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<PaymentResponseDTO> findAll(Pageable pageable) {
    return paymentRepository.findAll(pageable).map(PaymentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public PaymentResponseDTO findById(Long paymentId) {

    Payment payment = searchPaymentById(paymentId);

    return new PaymentResponseDTO(payment);
  }

  private Payment searchPaymentById(Long id) {
    return paymentRepository.findById(id).orElseThrow(
        () -> new BusinessException("Payment not found with id: " + id));
  }

}
