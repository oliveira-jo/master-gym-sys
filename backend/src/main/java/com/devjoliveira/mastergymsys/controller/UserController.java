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

import com.devjoliveira.mastergymsys.doc.UserControllerDoc;
import com.devjoliveira.mastergymsys.dto.UserRequestDTO;
import com.devjoliveira.mastergymsys.dto.UserResponseDTO;
import com.devjoliveira.mastergymsys.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController implements UserControllerDoc {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @SuppressWarnings("null")
  @GetMapping
  public ResponseEntity<Page<UserResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(userService.findAll(pageable));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO request) {
    UserResponseDTO savedUser = userService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedUser.id()).toUri();

    return ResponseEntity.created(uri).body(savedUser);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid UserRequestDTO request) {
    return ResponseEntity.ok().body(userService.update(id, request));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
