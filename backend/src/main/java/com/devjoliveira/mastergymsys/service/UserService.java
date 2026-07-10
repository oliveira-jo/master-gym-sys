package com.devjoliveira.mastergymsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Role;
import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.UserFilterRequest;
import com.devjoliveira.mastergymsys.dto.UserRequestDTO;
import com.devjoliveira.mastergymsys.dto.UserResponseDTO;
import com.devjoliveira.mastergymsys.mapper.UserMapper;
import com.devjoliveira.mastergymsys.projection.UserDetailsProjection;
import com.devjoliveira.mastergymsys.repositoty.UserRepository;
import com.devjoliveira.mastergymsys.specification.UserSpecification;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = encoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
    if (result.isEmpty()) {
      throw new UsernameNotFoundException("Email not found");
    }

    User user = new User();
    user.setEmail(username);
    user.setPassword(result.get(0).getPassword());

    result.forEach(
        role -> user.getRoles().add(new Role(role.getRoleId(), role.getAuthority())));

    return user;

  }

  @Transactional(readOnly = true)
  public Page<UserResponseDTO> findAll(UserFilterRequest filter, @NonNull Pageable pageable) {
    return userRepository.findAll(UserSpecification.withFilter(filter), pageable)
        .map(UserResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public UserResponseDTO findById(Long id) {
    return new UserResponseDTO(searchById(id));
  }

  @Transactional
  public UserResponseDTO save(UserRequestDTO userRequestDTO) {

    // Clean cpf
    String cpfRequest = userRequestDTO.cpf().replaceAll("\\D", "");

    // Verify if already has cpf and email registered
    Optional<User> existUser = userRepository.findByCpf(cpfRequest);

    if (existUser.isPresent())
      throw new BusinessException("This CPF is already registered");

    existUser = userRepository.findByEmail(userRequestDTO.email());
    if (existUser.isPresent())
      throw new BusinessException("This Email is already registered");

    // Encolde and save password
    String passEncode = passwordEncoder.encode(userRequestDTO.password());

    // Converto dto to entity
    User newUser = userMapper.toDomain(userRequestDTO, passEncode);
    newUser.setCpf(cpfRequest);

    // Save in Database
    User fromDB = userRepository.save(newUser);

    return new UserResponseDTO(fromDB);

  }

  @Transactional
  public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {

    User fromDB = searchById(id);

    fromDB.setName(userRequestDTO.name());
    // cpf can't be update
    fromDB.setPhone(userRequestDTO.phone());
    fromDB.setEmail(userRequestDTO.email());

    // Encolde and save password
    fromDB.setPassword(passwordEncoder.encode(userRequestDTO.password()));

    fromDB.setBirthdate(userRequestDTO.birthdate());
    fromDB.setAddress(userRequestDTO.address());
    fromDB.setNumber(userRequestDTO.number());
    fromDB.setComplement(userRequestDTO.complement());
    fromDB.setCity(userRequestDTO.city());
    fromDB.setState(userRequestDTO.state());
    fromDB.setZipCode(userRequestDTO.zipCode());

    return new UserResponseDTO(
        userRepository.save(fromDB));
  }

  @SuppressWarnings("null")
  @Transactional
  public void deleteById(Long id) {
    User fromDB = searchById(id);

    try {
      userRepository.delete(fromDB);
    } catch (Exception e) {
      throw new BusinessException("Error deleting user with id: " + id + " : " +
          e);
    }
  }

  @SuppressWarnings("null")
  private User searchById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found with id: " + id));
  }

}