

package com.app.controller;

import com.app.enties.Transaction;
import com.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class TransactionController {

  @Autowired private TransactionService transactionService;

  /*

    curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
    http://localhost:8080/api/do_transaction \
    -d '{"toaccountid":1, "fromaccountid": 1}'

  */
  @RequestMapping("/api/do_transaction")
  @PostMapping
  private Object doTransaction(@RequestBody Transaction transaction) {
      
      return transactionService.do_transactions(transaction);

  }
}
