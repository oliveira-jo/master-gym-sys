package com.devjoliveira.mastergymsys.dto;

import java.time.LocalDate;

import com.devjoliveira.mastergymsys.domain.Student;

public record StudentRequestDTO(

    String name,
    LocalDate birthdate,
    String genre,
    String phone,
    String email,
    String observations,
    String address,
    String number,
    String complement,
    String city,
    String state,
    String zipCode) {

  public StudentRequestDTO(Student student) {
    this(
        student.getName(),
        student.getBirthdate() != null ? student.getBirthdate() : null,
        student.getGenre(),
        student.getPhone(),
        student.getEmail(),
        student.getObservations(),
        student.getAddress(),
        student.getNumber(),
        student.getComplement(),
        student.getCity(),
        student.getState(),
        student.getZipCode());
  }

}