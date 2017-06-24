package com.app.repository;

import com.app.enties.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
}
