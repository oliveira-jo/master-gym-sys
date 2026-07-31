package com.devjoliveira.mastergymsys.mapper;

import org.springframework.stereotype.Component;

import com.devjoliveira.mastergymsys.domain.Student;
import com.devjoliveira.mastergymsys.dto.request.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.StudentResponseDTO;

@Component
public class StudentMapper {

  public StudentResponseDTO toDTO(Student student) {
    return new StudentResponseDTO(student);
  }

  public Student toDomain(StudentRequestDTO studentRequestDTO) {

    Student student = new Student();
    student.setName(studentRequestDTO.name());
    student.setBirthdate(studentRequestDTO.birthdate());
    student.setgender(studentRequestDTO.gender());
    student.setPhone(studentRequestDTO.phone());
    student.setEmail(studentRequestDTO.email());
    student.setCpf(studentRequestDTO.cpf());
    student.setObservation(studentRequestDTO.observation());
    student.setAddress(studentRequestDTO.address());
    student.setAddressNumber(studentRequestDTO.addressNumber());
    student.setComplement(studentRequestDTO.complement());
    student.setCity(studentRequestDTO.city());
    student.setStateCode(studentRequestDTO.stateCode());
    student.setZipCode(studentRequestDTO.zipCode());

    return student;

  }

}
