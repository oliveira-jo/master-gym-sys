package com.devjoliveira.mastergymsys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.EnrollmentModality;
import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.request.EnrollmentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.EnrollmentResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.EnrollmentModalityRepository;
import com.devjoliveira.mastergymsys.repositoty.EnrollmentRepository;
import com.devjoliveira.mastergymsys.repositoty.GraduationRepository;
import com.devjoliveira.mastergymsys.repositoty.ModalityRepository;
import com.devjoliveira.mastergymsys.repositoty.StudentRepository;
import com.devjoliveira.mastergymsys.repositoty.SubscriptionRepository;

@Service
public class EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;
  private final EnrollmentModalityRepository enrollmentModalityRepository;
  private final StudentRepository studentRepository;

  private final ModalityRepository modalityRepository;
  private final GraduationRepository graduationRepository;
  private final SubscriptionRepository subscriptionRepository;

  public EnrollmentService(
      EnrollmentRepository enrollmentRepository,
      EnrollmentModalityRepository enrollmentModalityRepository,
      StudentRepository studentRepository,
      ModalityRepository modalityRepository,
      GraduationRepository graduationRepository,
      SubscriptionRepository subscriptionRepository) {
    this.enrollmentRepository = enrollmentRepository;
    this.enrollmentModalityRepository = enrollmentModalityRepository;
    this.studentRepository = studentRepository;
    this.modalityRepository = modalityRepository;
    this.graduationRepository = graduationRepository;
    this.subscriptionRepository = subscriptionRepository;
  }

  @Transactional(readOnly = true)
  public Page<EnrollmentResponseDTO> findAll(@NonNull Pageable pageable) {
    return enrollmentRepository.findAll(pageable).map(EnrollmentResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public EnrollmentResponseDTO findById(Long id) {
    return new EnrollmentResponseDTO(verifyEnrollmentById(id));
  }

  @SuppressWarnings("null")
  @Transactional
  public EnrollmentResponseDTO save(EnrollmentRequestDTO request) {

    Student studentFromDB = verifyStudentById(request.studentId());

    Enrollment enrollment = new Enrollment();
    enrollment.setEnrollmentDate(request.enrollmentDate());
    enrollment.setDueDay(request.dueDay());
    enrollment.setClosingDate(request.closingDate());
    enrollment.setStudent(studentFromDB);

    // save Enrollment in DB
    Enrollment enrollmntFromDB = enrollmentRepository.save(enrollment);

    // EnrollmentModality
    List<EnrollmentModality> enrollmentModalities = request.enrollmentModalities().stream().map(
        dto -> {
          EnrollmentModality em = new EnrollmentModality();
          em.setModality(modalityRepository.findById(dto.modalityId()).get());
          em.setGraduation(graduationRepository.findById(dto.graduationId()).get());
          em.setSubscription(subscriptionRepository.findById(dto.subscriptionId()).get());
          em.setStartDate(dto.startDate());
          em.setEndDate(dto.endDate());
          em.setEnrollment(enrollmntFromDB);
          return em;
        }).toList();

    // Save EnrollmentsModalities in DB
    List<EnrollmentModality> emFromDB = enrollmentModalityRepository.saveAll(enrollmentModalities);

    // Mount response
    EnrollmentResponseDTO response = new EnrollmentResponseDTO(enrollmntFromDB, emFromDB);

    return response;

  }

  @Transactional
  public EnrollmentResponseDTO change(Long id, EnrollmentRequestDTO request) {

    Enrollment fromDB = verifyEnrollmentById(id);

    fromDB.setEnrollmentDate(request.enrollmentDate());
    fromDB.setDueDay(request.dueDay());
    fromDB.setClosingDate(request.closingDate());

    // enrollmentRepository.save(fromDB);
    Enrollment enrollmntFromDB = enrollmentRepository.save(fromDB);

    List<EnrollmentModality> enrollmentModalities = request.enrollmentModalities().stream().map(
        dto -> {
          EnrollmentModality em = new EnrollmentModality();
          em.setModality(modalityRepository.findById(dto.modalityId()).get());
          em.setGraduation(graduationRepository.findById(dto.graduationId()).get());
          em.setSubscription(subscriptionRepository.findById(dto.subscriptionId()).get());
          em.setStartDate(dto.startDate());
          em.setEndDate(dto.endDate());
          em.setEnrollment(enrollmntFromDB);
          return em;
        }).toList();

    System.out.println("-------> " + enrollmentModalities);

    // Clear all enrollments
    enrollmentModalityRepository.deleteByEnrollment(enrollmntFromDB);
    enrollmentModalityRepository.flush();

    // Upload EnrollmentsModalities in DB
    List<EnrollmentModality> emFromDB = enrollmentModalityRepository.saveAll(enrollmentModalities);

    // Mount response
    EnrollmentResponseDTO response = new EnrollmentResponseDTO(enrollmntFromDB, emFromDB);

    return response;

  }

  @SuppressWarnings("null")
  @Transactional
  public void deleteById(Long id) {
    Enrollment fromDB = verifyEnrollmentById(id);

    try {
      enrollmentRepository.delete(fromDB);
    } catch (Exception e) {
      throw new BusinessException("Error deleting enrollment with id: " + id + ":" + e);
    }
  }

  @SuppressWarnings("null")
  private Enrollment verifyEnrollmentById(Long id) {
    return enrollmentRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Enrollment not found with id: " + id));
  }

  @SuppressWarnings("null")
  private Student verifyStudentById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Enrollment not found with id: " + id));
  }

}