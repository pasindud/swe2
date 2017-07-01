

package com.app.controller;

import com.app.Utils;
import com.app.enties.Transaction;
import com.app.repository.TransactionRepository;
import com.app.request.TransactionRequest;
import com.app.service.TransactionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/** @author Pasindu */
@RestController
public class TransactionController {
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private TransactionService transactionService;

  @GetMapping("/api/transactions")
  private List<Transaction> getTransactions(@RequestParam("id") int accountId) {
    return transactionRepository.getAccountTransactions(accountId);
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
