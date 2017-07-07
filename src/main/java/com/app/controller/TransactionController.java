

package com.app.controller;

import com.app.Utils;
import com.app.annotation.Checkdb;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import com.app.request.TransactionRequest;
import com.app.service.AccountService;
import com.app.service.TransactionService;
import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/** @author Pasindu */
@RestController
public class TransactionController {
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private TransactionService transactionService;
  @Autowired private AccountService accountService;
  @Autowired private AccountRepository accountRepository;
  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

  private static class SimpleRequest{
    @NotNull
    @Checkdb(userCheck = true)
    Integer id;
  }
  
  /**
   * API endpoint to get transactions of a specific account
   * @param accountId
   * @return all transactions for accountId
   * curl -u xyz:xyz "http://localhost:8080/api/transactions?id=1"
   */
  @GetMapping("/api/transactions")
  private List<Map<String, Object>> getTransactions(@RequestParam("id") int accountId) {
    List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
    
    if (!accountService.checkUserHasPermissions(accountId, true)) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("error", "Account not found.");
        response.add(responseMap);
        return response;
    }
   // return transactionRepository.getAccountTransactions(accountId);
   return transactionService.getTransactionsPerAccount(accountId);
  }
  
  /**
   * API endpoint to get transactions of a specific account on specific day
   * @param accountId
   * @param fromDate
   * @return all transactions for accountId on specific day
   * curl -u xyz:xyz "http://localhost:8080/api/transactions_perday?id=1&from=2017-07-04"
   */
  @GetMapping("/api/transactions_perday")
  private List<Map<String, Object>> getTransactionsPerDay(@RequestParam("id") int accountId,@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate) {
    List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
    
    if (!accountService.checkUserHasPermissions(accountId, true)) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("error", "Account not found.");
        response.add(responseMap);
        return response;
    }
   // return transactionRepository.getAccountTransactions(accountId);
   return transactionService.getTransactionsPerAccountPerDay(accountId,fromDate);
  }
  
  /**
   * API endpoint to get transactions of a specific account on specific date range (Bank statement)
   * @param accountId
   * @param fromDate
   * @param toDate
   * @return all transactions for accountId on specific date range (Bank statement)
   * curl -u xyz:xyz "http://localhost:8080/api/transactions_perday?id=232&from=2017-07-04&to=2017-07-07"
   */
  @GetMapping("/api/transactions_statement")
  private List<Map<String, Object>> getTransactionsSatemnt(@RequestParam("id") int accountId,@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
    List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
    
    if (!accountService.checkUserHasPermissions(accountId, true)) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("error", "Account not found.");
        response.add(responseMap);
        return response;
    }
   // return transactionRepository.getAccountTransactions(accountId);
   return transactionService.getTransactionsStatement(accountId,fromDate,toDate);
  }

  /**
   * API endpoint to get transaction by id.
   * <p>
   *  curl -u xyz:xyz "http://localhost:8080/api/transaction_by_id?id=1"
   * <p/>
   * @param transactionId the id of the transaction to retrive the info for.
   * @return the details of the transaction id or a error map.
   */
  @GetMapping("/api/transaction_by_id")
  private Object getTransactionById(@RequestParam("id") int transactionId) {
    logger.info("HERERERERRE");
    try {
      return transactionService.getTransactionById(transactionId);
    } catch (Exception e) {
      e.printStackTrace();
      Map<String, String> response = new HashMap<>();
      response.put("error", "Transaction not found");
      return response;
    }
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
