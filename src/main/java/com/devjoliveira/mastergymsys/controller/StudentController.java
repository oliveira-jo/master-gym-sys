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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.mastergymsys.dto.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.StudentResponseDTO;
import com.devjoliveira.mastergymsys.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<Page<StudentResponseDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(studentService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<StudentResponseDTO> searchByName(@RequestParam(defaultValue = "") String name) {
    return ResponseEntity.ok(studentService.findByName(name));
  }

  @PostMapping
  public ResponseEntity<StudentResponseDTO> save(StudentRequestDTO request) {
    StudentResponseDTO savedStudent = studentService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedStudent.id()).toUri();

    return ResponseEntity.created(uri).body(savedStudent);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> change(@PathVariable Long id, @RequestBody StudentRequestDTO request) {
    return ResponseEntity.ok().body(studentService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    studentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
