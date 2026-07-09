package com.devjoliveira.mastergymsys.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devjoliveira.mastergymsys.domain.Role;
import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.projection.UserDetailsProjection;
import com.devjoliveira.mastergymsys.repositoty.UserRepository;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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

}