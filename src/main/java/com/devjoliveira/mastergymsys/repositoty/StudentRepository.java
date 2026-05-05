package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Page<Student> findAll(Pageable pageable);

  Optional<Student> findByName(String name);

}
