package com.devjoliveira.mastergymsys.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByAuthority(String authority);

}
