

package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class TransactionController {
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private AccountRepository accountRepository;

  /*

    curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
    http://localhost:8080/api/do_transaction \
    -d '{"toaccountid":1, "fromaccountid": 1}'

  */
  @RequestMapping("/api/do_transaction")
  @PostMapping
  private Object doTransaction(@RequestBody Transaction transaction) {

    Account fromAccount = accountRepository.findByAccountid(transaction.getFromaccountid());

    if (fromAccount == null) {
      return "Error no from account found";
    }

    Account toAccount = accountRepository.findByAccountid(transaction.getToaccountid());

    if (toAccount == null) {
      return "Error no to account found";
    }

    return "nothing";
  }
}
