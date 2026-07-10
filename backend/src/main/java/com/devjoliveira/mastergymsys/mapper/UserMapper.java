package com.devjoliveira.mastergymsys.mapper;

import org.springframework.stereotype.Component;

import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.dto.UserRequestDTO;
import com.devjoliveira.mastergymsys.dto.UserResponseDTO;

@Component
public class UserMapper {

  public UserResponseDTO toDTO(User user) {
    return new UserResponseDTO(user);
  }

  public User toDomain(UserRequestDTO userRequestDTO, String passwordEncode) {

    User user = new User();
    user.setName(userRequestDTO.name());
    user.setCpf(userRequestDTO.cpf());
    user.setPhone(userRequestDTO.phone());
    user.setEmail(userRequestDTO.email());
    user.setPassword(passwordEncode);
    user.setBirthdate(userRequestDTO.birthdate());
    user.setAddress(userRequestDTO.address());
    user.setNumber(userRequestDTO.number());
    user.setComplement(userRequestDTO.complement());
    user.setCity(userRequestDTO.city());
    user.setState(userRequestDTO.state());
    user.setZipCode(userRequestDTO.zipCode());

    return user;

  }

}
