

package com.app.repository;

import com.app.enties.LoginHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/** @author Pasindu */
public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {

  @Query(
          value = "select * from login_history where createdtime >= DATE_SUB(NOW(), INTERVAL 10 MINUTE) and username = ?1",
          nativeQuery = true
  )
  List<LoginHistory> getLast10MinsLoginHistoryByUsername(String username);
 }
