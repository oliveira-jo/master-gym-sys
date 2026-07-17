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

import com.devjoliveira.mastergymsys.doc.StudentControllerDoc;
import com.devjoliveira.mastergymsys.dto.request.StudentFilterRequest;
import com.devjoliveira.mastergymsys.dto.request.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.StudentResponseDTO;
import com.devjoliveira.mastergymsys.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT')")
public class StudentController implements StudentControllerDoc {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<Page<StudentResponseDTO>> findAll(StudentFilterRequest filter, Pageable pageable) {
    return ResponseEntity.ok(studentService.findAll(filter, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<StudentResponseDTO> save(@RequestBody @Valid StudentRequestDTO request) {
    StudentResponseDTO savedStudent = studentService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedStudent.id()).toUri();

    return ResponseEntity.created(uri).body(savedStudent);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> change(@PathVariable Long id,
      @RequestBody @Valid StudentRequestDTO request) {
    return ResponseEntity.ok().body(studentService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    studentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
