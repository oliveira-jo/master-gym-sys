package com.devjoliveira.mastergymsys.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.dto.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.StudentResponseDTO;
import com.devjoliveira.mastergymsys.mapper.StudentMapper;
import com.devjoliveira.mastergymsys.repositoty.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Transactional(readOnly = true)
  public Page<StudentResponseDTO> findAll(Pageable pageable) {
    return studentRepository.findAll(pageable).map(StudentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public StudentResponseDTO findById(Long id) {
    return studentRepository.findById(id)
        .map(StudentResponseDTO::new)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
  }

  @Transactional(readOnly = true)
  public StudentResponseDTO findByName(String name) {
    return studentRepository.findByName(name)
        .map(StudentResponseDTO::new)
        .orElseThrow(() -> new RuntimeException("Student not found with name: " + name));
  }

  @Transactional
  public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
    Student student = studentMapper.toDomain(studentRequestDTO);
    Student fromDB = studentRepository.save(student);
    return new StudentResponseDTO(fromDB);

  }

  @Transactional
  public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
    Student fromDB = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

    fromDB = studentMapper.toDomain(studentRequestDTO);

    Student updated = studentRepository.save(fromDB);
    return new StudentResponseDTO(updated);
  }

  @Transactional
  public void deleteById(Long id) {
    Student fromDB = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

    try {
      studentRepository.delete(fromDB);
    } catch (Exception e) {
      throw new RuntimeException("Error deleting student with id: " + id, e);
    }
  }

}
