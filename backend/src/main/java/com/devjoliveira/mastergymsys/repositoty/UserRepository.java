package com.devjoliveira.mastergymsys.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.projection.UserDetailsProjection;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query(nativeQuery = true, value = """
				SELECT users.email AS username, users.password, roles.id AS roleId, roles.authority
				FROM users
				INNER JOIN user_role ON users.id = user_role.user_id
				INNER JOIN roles ON roles.id = user_role.role_id
				WHERE users.email = :email
			""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

	@NonNull
	Page<User> findAll(@NonNull Pageable pageable);

	Optional<User> findByCpf(String cpf);

	Optional<User> findByEmail(String cpf);

}