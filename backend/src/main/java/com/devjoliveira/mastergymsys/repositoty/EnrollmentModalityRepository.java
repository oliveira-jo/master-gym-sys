package com.devjoliveira.mastergymsys.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.EnrollmentModality;
import java.util.List;

public interface EnrollmentModalityRepository extends JpaRepository<EnrollmentModality, Long> {

  List<EnrollmentModality> findByEnrollment(Enrollment enrollment);

  void deleteByEnrollment(Enrollment enrollment);

}
