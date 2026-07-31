package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.Student;

public record StudentResponseDTO(
    Long id,
    String name,
    String birthdate,
    String gender,
    String phone,
    String email,
    String cpf,
    String observation,
    String address,
    String addressNumber,
    String complement,
    String city,
    String stateCode,
    String zipCode) {

  public StudentResponseDTO(Student student) {
    this(
        student.getId(),
        student.getName(),
        student.getBirthdate() != null ? student.getBirthdate().toString() : null,
        student.getgender(),
        student.getPhone(),
        student.getEmail(),
        student.getCpf(),
        student.getObservation(),
        student.getAddress(),
        student.getAddressNumber(),
        student.getComplement(),
        student.getCity(),
        student.getStateCode(),
        student.getZipCode());
  }

}