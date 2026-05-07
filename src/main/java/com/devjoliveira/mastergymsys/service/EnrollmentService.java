package com.devjoliveira.mastergymsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.dto.EnrollmentRequestDTO;
import com.devjoliveira.mastergymsys.dto.EnrollmentResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.EnrollmentRepository;
import com.devjoliveira.mastergymsys.repositoty.StudentRepository;

@Service
public class EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;
  private final StudentRepository studentRepository;

  public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository) {
    this.enrollmentRepository = enrollmentRepository;
    this.studentRepository = studentRepository;
  }

  @Transactional(readOnly = true)
  public Page<EnrollmentResponseDTO> findAll(Pageable pageable) {
    return enrollmentRepository.findAll(pageable).map(EnrollmentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public EnrollmentResponseDTO findById(Long id) {
    return new EnrollmentResponseDTO(searchById(id));
  }

  @Transactional
  public EnrollmentResponseDTO save(EnrollmentRequestDTO request) {

    Long studentId;
    try {
      studentId = Long.valueOf(request.studentId());
    } catch (NumberFormatException e) {
      throw new RuntimeException("Invalid modality id: " + request.studentId(), e);
    }

    Student studentFromDB = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

    Enrollment enrollment = new Enrollment();
    enrollment.setEnrollmentDate(request.enrollmentDate());
    enrollment.setDueDay(request.dueDay());
    enrollment.setClosingDate(request.closingDate());
    enrollment.setStudent(studentFromDB);

    Enrollment fromDB = enrollmentRepository.save(enrollment);
    return new EnrollmentResponseDTO(fromDB);

  }

  @Transactional
  public EnrollmentResponseDTO change(Long id, EnrollmentRequestDTO request) {

    Enrollment fromDB = searchById(id);

    fromDB.setEnrollmentDate(request.enrollmentDate());
    fromDB.setDueDay(request.dueDay());
    fromDB.setClosingDate(request.closingDate());

    enrollmentRepository.save(fromDB);

    return new EnrollmentResponseDTO(fromDB);

  }

  @Transactional
  public void deleteById(Long id) {
    Enrollment fromDB = searchById(id);

    try {
      enrollmentRepository.delete(fromDB);
    } catch (Exception e) {
      throw new RuntimeException("Error deleting enrollment with id: " + id, e);
    }
  }

  private Enrollment searchById(Long id) {
    return enrollmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
  }
}