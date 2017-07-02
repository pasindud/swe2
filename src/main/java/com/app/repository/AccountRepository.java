/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.repository;

import com.app.enties.Account;
import com.app.enties.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/** @author Pasindu */
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByUserid(Users users);

  Account findByAccountid(int id);

  @Modifying(clearAutomatically = true)
  @Query("UPDATE Account c SET c.locked = ?1 WHERE c.accountid = ?2")
  int updateAccountLock(boolean lock, int accountid);
}
