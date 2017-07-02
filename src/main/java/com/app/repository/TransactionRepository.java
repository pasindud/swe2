

package com.app.repository;

import com.app.enties.MerchantServices;
import com.app.enties.Transaction;
import com.app.enties.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** @author Pasindu */
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
  @Query(
          value = "SELECT * FROM Transaction t WHERE t.fromaccountid=?1 or t.toaccountid=?1",
          nativeQuery = true
  )
  public List<Transaction> getAccountTransactions(int accountid);
  public Transaction findByTransactionid(int transactionid);
}
