package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Page<Student> findAll(Pageable pageable);

  Optional<Student> findByCpf(String cpf);

  Optional<Student> findByEmail(String cpf);

  Optional<Student> findByNameContainingIgnoreCase(String name);

}
