package com.devjoliveira.mastergymsys.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.request.StudentFilterRequest;
import com.devjoliveira.mastergymsys.dto.request.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.StudentResponseDTO;
import com.devjoliveira.mastergymsys.mapper.StudentMapper;
import com.devjoliveira.mastergymsys.repositoty.StudentRepository;
import com.devjoliveira.mastergymsys.specification.StudentSpecification;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Transactional(readOnly = true)
  public Page<StudentResponseDTO> findAll(StudentFilterRequest filter, @NonNull Pageable pageable) {
    return studentRepository.findAll(StudentSpecification.withFilter(filter), pageable)
        .map(StudentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public StudentResponseDTO findById(Long id) {
    return new StudentResponseDTO(searchById(id));
  }

  @Transactional
  public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {

    // Clean cpf
    String cpfRequest = studentRequestDTO.cpf().replaceAll("\\D", "");

    // Verify if already has cpf and email registered
    Optional<Student> existStudent = studentRepository.findByCpf(cpfRequest);
    if (existStudent.isPresent())
      throw new BusinessException("This CPF is already registered");

    existStudent = studentRepository.findByEmail(studentRequestDTO.email());
    if (existStudent.isPresent())
      throw new BusinessException("This Email is already registered");

    // Converto dto to entity
    Student newStudent = studentMapper.toDomain(studentRequestDTO);
    newStudent.setCpf(cpfRequest);

    // Save in Database
    Student fromDB = studentRepository.save(newStudent);

    return new StudentResponseDTO(fromDB);

  }

  @Transactional
  public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
    Student fromDB = searchById(id);

    fromDB.setName(studentRequestDTO.name());
    fromDB.setBirthdate(studentRequestDTO.birthdate());
    fromDB.setGenre(studentRequestDTO.genre());
    fromDB.setPhone(studentRequestDTO.phone());
    fromDB.setEmail(studentRequestDTO.email());
    // cpf can't be update
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

  @SuppressWarnings("null")
  @Transactional
  public void deleteById(Long id) {
    Student fromDB = searchById(id);

    try {
      studentRepository.delete(fromDB);
    } catch (Exception e) {
      throw new BusinessException("Error deleting student with id: " + id + " : " + e);
    }
  }

  @SuppressWarnings("null")
  private Student searchById(Long id) {
    return studentRepository.findById(id).orElseThrow(() -> new BusinessException("Student not found with id: " + id));
  }

}
