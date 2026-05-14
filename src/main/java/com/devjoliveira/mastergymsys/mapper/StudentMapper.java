package com.devjoliveira.mastergymsys.mapper;

import org.springframework.stereotype.Component;

import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.dto.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.StudentResponseDTO;

@Component
public class StudentMapper {

  public StudentResponseDTO toDTO(Student student) {
    return new StudentResponseDTO(student);
  }

  public Student toDomain(StudentRequestDTO studentRequestDTO) {

    Student student = new Student();
    student.setName(studentRequestDTO.name());
    student.setBirthdate(studentRequestDTO.birthdate());
    student.setGenre(studentRequestDTO.genre());
    student.setPhone(studentRequestDTO.phone());
    student.setEmail(studentRequestDTO.email());
    student.setCpf(studentRequestDTO.cpf());
    student.setObservations(studentRequestDTO.observations());
    student.setAddress(studentRequestDTO.address());
    student.setNumber(studentRequestDTO.number());
    student.setComplement(studentRequestDTO.complement());
    student.setCity(studentRequestDTO.city());
    student.setState(studentRequestDTO.state());
    student.setZipCode(studentRequestDTO.zipCode());

    return student;

  }

}
