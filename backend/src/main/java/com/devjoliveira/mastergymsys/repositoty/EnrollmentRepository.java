package com.devjoliveira.mastergymsys.repositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devjoliveira.mastergymsys.domain.Enrollment;
import com.devjoliveira.mastergymsys.domain.enums.StatusEnrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

  @Query("""
        SELECT e
        FROM Enrollment e
        JOIN e.student s
        WHERE
        (:search IS NULL OR :search = '')
        OR
        LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))
        OR
        LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%'))
        OR
        CAST(e.id AS string) = :search
      """)
  Page<Enrollment> search(String search, Pageable pageable);

  long countByStatus(StatusEnrollment status);

}
