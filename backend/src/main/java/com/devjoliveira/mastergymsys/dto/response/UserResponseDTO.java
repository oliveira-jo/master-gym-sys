package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.User;

public record UserResponseDTO(
    Long id,
    String name,
    String cpf,
    String phone,
    String email,
    String birthdate,
    String address,
    String addressNumber,
    String complement,
    String city,
    String stateCode,
    String zipCode) {

  public UserResponseDTO(User user) {
    this(
        user.getId(),
        user.getName(),
        user.getCpf(),
        user.getPhone(),
        user.getEmail(),
        user.getBirthdate() != null ? user.getBirthdate().toString() : null,
        user.getAddress(),
        user.getAddressNumber(),
        user.getComplement(),
        user.getCity(),
        user.getStateCode(),
        user.getZipCode());
  }

}