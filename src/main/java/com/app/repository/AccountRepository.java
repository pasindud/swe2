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
/** @author Pasindu */
public interface AccountRepository extends JpaRepository<Account, Long> {
  //    List<Account> findByUsersid(Users users);
  List<Account> findByUserid(Users users);
}
