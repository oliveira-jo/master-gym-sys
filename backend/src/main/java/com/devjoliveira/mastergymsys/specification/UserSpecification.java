package com.devjoliveira.mastergymsys.specification;

import org.springframework.data.jpa.domain.Specification;

import com.devjoliveira.mastergymsys.domain.User;
import com.devjoliveira.mastergymsys.dto.request.UserFilterRequest;

public class UserSpecification {

  public static Specification<User> withFilter(UserFilterRequest filter) {

    return Specification.<User>unrestricted()
        .and(nameContem(filter.name()))
        .and(emailContem(filter.email()))
        .and(phoneContem(filter.phone()));

  }

  private static Specification<User> nameContem(String name) {
    return (root, query, cb) -> {
      if (name == null || name.isBlank()) {
        return null;
      }
      return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    };

  }

  private static Specification<User> emailContem(String email) {
    return (root, query, cb) -> {
      if (email == null || email.isBlank()) {
        return null;
      }
      return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    };
  }

  private static Specification<User> phoneContem(String phone) {
    return (root, query, cb) -> {
      if (phone == null || phone.isBlank()) {
        return null;
      }
      return cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%");
    };
  }

}
