package com.app.repository;

import com.app.enties.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
  Users save(Users users);
  Users findByUserId(int userId);
}
