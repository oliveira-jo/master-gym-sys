package com.devjoliveira.mastergymsys.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.mastergymsys.dto.SubscriptionRequestDTO;
import com.devjoliveira.mastergymsys.dto.SubscriptionResponseDTO;
import com.devjoliveira.mastergymsys.service.SubscriptionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @GetMapping
  public ResponseEntity<Page<SubscriptionResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(subscriptionService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubscriptionResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(subscriptionService.findById(id));
  }

  @PostMapping
  public ResponseEntity<SubscriptionResponseDTO> save(@RequestBody @Valid SubscriptionRequestDTO request) {
    SubscriptionResponseDTO savedSubscription = subscriptionService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedSubscription.id()).toUri();

    return ResponseEntity.created(uri).body(savedSubscription);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubscriptionResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid SubscriptionRequestDTO request) {
    return ResponseEntity.ok().body(subscriptionService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    subscriptionService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
