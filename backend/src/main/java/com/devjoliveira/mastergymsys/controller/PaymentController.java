package com.devjoliveira.mastergymsys.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.mastergymsys.dto.request.PaymentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.PaymentResponseDTO;
import com.devjoliveira.mastergymsys.service.PaymentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/payments")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @GetMapping
  public ResponseEntity<Page<PaymentResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok().body(paymentService.findAll(pageable));
  }

  @GetMapping("/open")
  public ResponseEntity<Page<PaymentResponseDTO>> findOpenPayments(Pageable pageable) {
    return ResponseEntity.ok().body(paymentService.findOpenPayments(pageable));
  }

  @GetMapping("/overdue")
  public ResponseEntity<Page<PaymentResponseDTO>> findOverduePayments(Pageable pageable) {
    return ResponseEntity.ok().body(paymentService.findOverduePayments(pageable));
  }

  @GetMapping("/enrollment/{id}")
  public ResponseEntity<Page<PaymentResponseDTO>> findByEnrollmentId(Pageable pageable, @PathVariable Long id) {
    return ResponseEntity.ok().body(paymentService.findByEnrollmentId(pageable, id));
  }

  @PostMapping
  public ResponseEntity<PaymentResponseDTO> save(@RequestBody @Valid PaymentRequestDTO request) {
    PaymentResponseDTO savedPayment = paymentService.create(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedPayment.id()).toUri();

    return ResponseEntity.created(uri).body(savedPayment);
  }

  @PutMapping("/{id}/pay")
  public ResponseEntity<Void> pay(@PathVariable Long id) {
    paymentService.pay(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/cancel")
  public ResponseEntity<Void> cancel(@PathVariable Long id) {
    paymentService.cancel(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(paymentService.findById(id));
  }

}
