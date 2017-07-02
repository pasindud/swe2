

package com.app.controller;

import com.app.Utils;
import com.app.annotation.Checkdb;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import com.app.request.TransactionRequest;
import com.app.service.AccountService;
import com.app.service.TransactionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/** @author Pasindu */
@RestController
public class TransactionController {
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private TransactionService transactionService;
  @Autowired private AccountService accountService;
  @Autowired private AccountRepository accountRepository;
  private static class SimpleRequest{
    @NotNull
    @Checkdb(userCheck = true)
    Integer id;
  }

  @GetMapping("/api/transactions")
  private Object getTransactions(@RequestParam("id") int accountId) {
    Map<String, String> response = new HashMap<>();
    if (!accountService.checkUserHasPermissions(accountId, true)) {
      return response.put("errors", "Access denied");
    }
    return transactionRepository.getAccountTransactions(accountId);
  }

  /**
   * API endpoint to get transaction by id.
   * @param transactionId the id of the transaction to retrive the info for.
   * @return the details of the transaction id or a error map.
   */
  @GetMapping("/api/transaction_by_id")
  private Object getTransactionById(@RequestParam("id") int transactionId) {
    return transactionService.getTransactionById(transactionId);
  }

  /*

    curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
    http://localhost:8080/api/do_transaction \
    -d '{"toaccountid":1, "fromaccountid": 1}'

  */
  @RequestMapping("/api/do_transaction")
  @PostMapping
  private Map<String, List<String>> doTransaction(
      @Valid @RequestBody TransactionRequest transactionRequest, Errors requestError)
      throws Exception {
    Map<String, List<String>> response = new HashMap<String, List<String>>();

    if (requestError.hasErrors()) {
      response.put("errors", Utils.getListFromErrors(requestError));
      return response;
    }
    response.put("errors", transactionService.do_transactions(transactionRequest));
    return response;
  }
}
