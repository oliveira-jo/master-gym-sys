package com.devjoliveira.mastergymsys.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
