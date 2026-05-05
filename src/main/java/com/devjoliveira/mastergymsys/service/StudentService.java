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
    return studentRepository.findByNameContainingIgnoreCase(name)
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
    Student fromDB = studentRepository.getReferenceById(id);

    if (fromDB == null) {
      throw new RuntimeException("Student not found with id: " + id);
    }

    fromDB.setName(studentRequestDTO.name());
    fromDB.setBirthdate(studentRequestDTO.birthdate());
    fromDB.setGenre(studentRequestDTO.genre());
    fromDB.setPhone(studentRequestDTO.phone());
    fromDB.setEmail(studentRequestDTO.email());
    fromDB.setObservations(studentRequestDTO.observations());
    fromDB.setAddress(studentRequestDTO.address());
    fromDB.setNumber(studentRequestDTO.number());
    fromDB.setComplement(studentRequestDTO.complement());
    fromDB.setCity(studentRequestDTO.city());
    fromDB.setState(studentRequestDTO.state());
    fromDB.setZipCode(studentRequestDTO.zipCode());

    return new StudentResponseDTO(
        studentRepository.save(fromDB));
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
