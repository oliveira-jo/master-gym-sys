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

import com.devjoliveira.mastergymsys.doc.ModalityControllerDoc;
import com.devjoliveira.mastergymsys.dto.request.ModalityRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.ModalityResponseDTO;
import com.devjoliveira.mastergymsys.service.ModalityService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/modalities")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
public class ModalityController implements ModalityControllerDoc {

  private final ModalityService modalityService;

  public ModalityController(ModalityService modalityService) {
    this.modalityService = modalityService;
  }

  @GetMapping
  public ResponseEntity<Page<ModalityResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(modalityService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ModalityResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(modalityService.findById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<ModalityResponseDTO> searchByName(@RequestParam(defaultValue = "") String name) {
    return ResponseEntity.ok(modalityService.findByName(name));
  }

  @PostMapping
  public ResponseEntity<ModalityResponseDTO> save(@RequestBody @Valid ModalityRequestDTO request) {
    ModalityResponseDTO savedModality = modalityService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedModality.id()).toUri();

    return ResponseEntity.created(uri).body(savedModality);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ModalityResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid ModalityRequestDTO request) {
    return ResponseEntity.ok().body(modalityService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    modalityService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
