package com.devjoliveira.mastergymsys.dto.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.devjoliveira.mastergymsys.domain.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequestDTO(

    @NotBlank(message = "Name is required") String name,

    @Past(message = "Birthdate need to be in past") LocalDate birthdate,

    @NotBlank(message = "Genre is required") @Size(max = 1, message = "The genre must have a maximum of 1 caracter") String genre,

    @NotBlank(message = "Phone is required")

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9\\d{4}-?\\d{4}$", message = "Invalid phone format") String phone,

    @Email(message = "Invalid email format") @Size(max = 150, message = "The mail must have a maximum of 150 caracters") String email,

    @NotBlank(message = "CPF is required") @CPF(message = "Invalid CPF number!") String cpf,

    String observations,

    @NotBlank(message = "Name is required") @Size(max = 150, message = "The addres must have a maximum of 150 caracteres") String address,

    @NotBlank(message = "Number is required") @Size(max = 20, message = "The number must have a maximum of 20 caracteres") String number,

    @Size(max = 20, message = "The addres must have a maximum of 20 caracteres") String complement,

    @NotBlank(message = "City is required") @Size(max = 100, message = "The city must have a maximum of 100 caracteres") String city,

    @NotBlank(message = "State is required") @Size(max = 2, message = "The state must have a maximum of 2 caracteres") String state,

    @NotBlank(message = "Zip code is required") @Size(max = 20, message = "The cep must have a maximum of 20 caracteres") String zipCode) {

  public StudentRequestDTO(Student student) {
    this(
        student.getName(),
        student.getBirthdate() != null ? student.getBirthdate() : null,
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