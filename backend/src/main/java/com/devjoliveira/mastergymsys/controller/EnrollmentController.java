package com.devjoliveira.mastergymsys.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.mastergymsys.doc.EnrollmentControllerDoc;
import com.devjoliveira.mastergymsys.dto.EnrollmentRequestDTO;
import com.devjoliveira.mastergymsys.dto.EnrollmentResponseDTO;
import com.devjoliveira.mastergymsys.service.EnrollmentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController implements EnrollmentControllerDoc {

  private final EnrollmentService enrollmentService;

  public EnrollmentController(EnrollmentService enrollmentService) {
    this.enrollmentService = enrollmentService;
  }

  @SuppressWarnings("null")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
  @GetMapping
  public ResponseEntity<Page<EnrollmentResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(enrollmentService.findAll(pageable));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
  @GetMapping("/{id}")
  public ResponseEntity<EnrollmentResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(enrollmentService.findById(id));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
  @PostMapping
  public ResponseEntity<EnrollmentResponseDTO> save(@RequestBody @Valid EnrollmentRequestDTO request) {
    EnrollmentResponseDTO response = enrollmentService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(uri).body(response);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
  @PutMapping("/{id}")
  public ResponseEntity<EnrollmentResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid EnrollmentRequestDTO request) {
    return ResponseEntity.ok().body(enrollmentService.change(id, request));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    enrollmentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
