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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.mastergymsys.doc.GraduationControllerDoc;
import com.devjoliveira.mastergymsys.dto.request.GraduationRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.GraduationResponseDTO;
import com.devjoliveira.mastergymsys.service.GraduationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/graduations")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
public class GraduationController implements GraduationControllerDoc {

  private final GraduationService graduationService;

  public GraduationController(GraduationService graduationService) {
    this.graduationService = graduationService;
  }

  @GetMapping
  public ResponseEntity<Page<GraduationResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(graduationService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GraduationResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(graduationService.findById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<GraduationResponseDTO> searchByName(@RequestParam(defaultValue = "") String name) {
    return ResponseEntity.ok(graduationService.findByName(name));
  }

  @PostMapping
  public ResponseEntity<GraduationResponseDTO> save(@RequestBody @Valid GraduationRequestDTO request) {
    GraduationResponseDTO savedGraduation = graduationService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedGraduation.id()).toUri();

    return ResponseEntity.created(uri).body(savedGraduation);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GraduationResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid GraduationRequestDTO request) {
    return ResponseEntity.ok().body(graduationService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    graduationService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
