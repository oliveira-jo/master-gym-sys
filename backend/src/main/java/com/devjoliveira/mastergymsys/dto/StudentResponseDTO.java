package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Student;

public record StudentResponseDTO(
    Long id,
    String name,
    String birthdate,
    String genre,
    String phone,
    String email,
    String cpf,
    String observations,
    String address,
    String number,
    String complement,
    String city,
    String state,
    String zipCode) {

  public StudentResponseDTO(Student student) {
    this(
        student.getId(),
        student.getName(),
        student.getBirthdate() != null ? student.getBirthdate().toString() : null,
        student.getGenre(),
        student.getPhone(),
        student.getEmail(),
        student.getCpf(),
        student.getObservations(),
        student.getAddress(),
        student.getNumber(),
        student.getComplement(),
        student.getCity(),
        student.getState(),
        student.getZipCode());
  }

}