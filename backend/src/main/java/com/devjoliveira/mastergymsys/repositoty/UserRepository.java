package com.devjoliveira.mastergymsys.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.projection.UserDetailsProjection;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = """
				SELECT users.email AS username, users.password, roles.id AS roleId, roles.authority
				FROM users
				INNER JOIN user_role ON users.id = user_role.user_id
				INNER JOIN roles ON roles.id = user_role.role_id
				WHERE users.email = :email
			""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

}