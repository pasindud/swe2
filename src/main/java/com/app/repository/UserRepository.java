package com.app.repository;

import com.app.enties.SpringUserStatic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SpringUserStatic, Long> {
  SpringUserStatic findByUsername(String username);
}
