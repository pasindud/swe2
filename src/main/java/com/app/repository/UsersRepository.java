package com.app.repository;

import com.app.enties.Account;
import com.app.enties.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
  Users save(Users users);
  Users findByUserId(int userId);

  @Query(
          value = "Select * From Users",
          nativeQuery = true
  )
  public List<Users> getAllUsers();

  @Modifying(clearAutomatically = true)
  @Query("UPDATE Users c SET c.locked = ?1 WHERE c.userId = ?2")
  void updateActivate(boolean locked, int user_id);
}
