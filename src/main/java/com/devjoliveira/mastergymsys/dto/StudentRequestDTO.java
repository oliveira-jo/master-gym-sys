package com.devjoliveira.mastergymsys.dto;

import java.time.LocalDate;

import com.devjoliveira.mastergymsys.domain.Student;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StudentRequestDTO(

    @NotBlank(message = "Name is required") String name,
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthdate,
    @NotBlank(message = "Genre is required") String genre,
    @NotBlank(message = "Phone is required") @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9\\d{4}-?\\d{4}$", message = "Invalid phone format") String phone,
    @Email(message = "Invalid email format") String email,
    String observations,
    @NotBlank(message = "Name is required") String address,
    @NotBlank(message = "Number is required") String number,
    String complement,
    @NotBlank(message = "City is required") String city,
    @NotBlank(message = "State is required") String state,
    @NotBlank(message = "Zip code is required") String zipCode) {

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