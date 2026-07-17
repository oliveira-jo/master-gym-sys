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
import com.devjoliveira.mastergymsys.dto.request.UserFilterRequest;
import com.devjoliveira.mastergymsys.dto.request.UserRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.UserResponseDTO;
import com.devjoliveira.mastergymsys.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController implements UserControllerDoc {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<UserResponseDTO>> findAll(UserFilterRequest filter, Pageable pageable) {
    return ResponseEntity.ok(userService.findAll(filter, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO request) {
    UserResponseDTO savedUser = userService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedUser.id()).toUri();

    return ResponseEntity.created(uri).body(savedUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid UserRequestDTO request) {
    return ResponseEntity.ok().body(userService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
