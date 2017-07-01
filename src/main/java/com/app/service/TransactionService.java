

package com.app.service;

import com.app.enties.Account;
import com.app.enties.Transaction;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import com.app.request.TransactionRequest;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** @author Pasindu */
@Service
public class TransactionService {
  @Autowired private AccountRepository accountRepository;
  @Autowired private TransactionRepository transactionRepository;

  private Transaction transaction;
  private List<String> errors = new ArrayList<String>();
  private Account fromAccount;
  private Account toAccount;

  private final String BANK_DEFAULT_CURRENCY = "USD";

  public TransactionService() {}

  public List<String> do_transactions(Transaction transaction) throws Exception {
    this.transaction = transaction;
    processTransaction();
    return errors;
  }

  public List<String> do_transactions(TransactionRequest transactionRequest) throws Exception {
    errors = new ArrayList<String>();
    this.transaction = new Transaction();
    this.transaction.setToaccountid(transactionRequest.getToaccount());
    this.transaction.setFromaccountid(transactionRequest.getFromaccountid());
    this.transaction.setAmount(transactionRequest.getAmount());
    this.transaction.setMessage(transactionRequest.getMessage());
    // TODO Add currency to transaction object.
    processTransaction();
    return errors;
  }

  private void processTransaction() throws Exception {
    this.fromAccount = accountRepository.findByAccountid(transaction.getFromaccountid());
    this.toAccount = accountRepository.findByAccountid(transaction.getToaccountid());

    if (validate()) {
      saveTransaction();
      updateAccounts();
    }
  }

  private boolean validate() throws Exception {
    if (validateRequired()) {
      getCurrencyRate();
      if ((getTransactionTypeEqT() || getTransactionTypeEqW())) {
        if (!checkUsersHasMoney()) return false;
      }
    } else {
      return false;
    }
    return true;
  }

  private boolean checkUsersHasMoney() {
    if (fromAccount.getBalance() <= getTransactionAmoutFromRate()) {
      errors.add("Not enough balance");
      return false;
    }
    if ((fromAccount.getAccTypeId().getDailyWithdrawLimit()) < (transaction.getAmount())) {
      errors.add("Exceeds daily withdrawal limit");
      return false;
    }
    if ((fromAccount.getBalance() - getTransactionAmoutFromRate())
        < fromAccount.getAccTypeId().getMinInitBalance()) {
      errors.add(
          "At least "
              + fromAccount.getAccTypeId().getMinInitBalance().toString()
              + " should be left in the account");
      return false;
    }
    return true;
  }

  private boolean getTransactionTypeEqW() {
    return transaction.getTranstype().equalsIgnoreCase("W");
  }

  private boolean getTransactionTypeEqT() {
    return transaction.getTranstype().equalsIgnoreCase("T");
  }
  
  private boolean getTransactionTypeEqD() {
    return transaction.getTranstype().equalsIgnoreCase("D");
  }
  
  private boolean getTransactionTypeEqTOrW() {
    return getTransactionTypeEqT() || getTransactionTypeEqW();
  }
  
  private Float getTransactionAmoutFromRate(){
      return this.transaction.getAmount() * this.transaction.getFromrate();
  }

  private Float getTransactionAmoutToRate(){
      return this.transaction.getAmount() * this.transaction.getTorate();
  }
  /**
   * Check whether the required fields our there.
   *
   * @return
   */
  private boolean validateRequired() {
    if (transaction.getTranstype() == null) {
      errors.add("Transaction type is not set.");
      return false;
    }
    if (transaction.getToaccountid() == 0 && !getTransactionTypeEqW()) {
      errors.add("Receiver account number is not valid.");
      return false;
    }

    if (getTransactionTypeEqTOrW()) {
      if (transaction.getFromaccountid() == 0) {
        errors.add("Sender account number is not valid..");
        return false;
      }
    }
    if (transaction.getAmount() == null) {
      errors.add("Transfer amount is not valid..");
      return false;
    }
    if (toAccount == null && !getTransactionTypeEqW()) {
      errors.add("Receiver account account not found");
      return false;
    }

    if (getTransactionTypeEqTOrW()) {
      if (fromAccount == null) {
        errors.add("Sender account found");
        return false;
      }
      if (transaction.getFromcurrency() == null) {
        transaction.setFromcurrency(fromAccount.getCurrency());
      }
    }
    if (transaction.getTocurrency() == null && !getTransactionTypeEqW()) {
      transaction.setTocurrency(toAccount.getCurrency());
    }

    return true;
  }

  private void saveTransaction() {
    transactionRepository.save(transaction);
  }

  private void updateAccounts() {
    if (getTransactionTypeEqTOrW()) {
      fromAccount.setBalance(
          fromAccount.getBalance() - getTransactionAmoutFromRate());
      accountRepository.save(fromAccount);
    }
    if (getTransactionTypeEqT() || getTransactionTypeEqD()) {
      toAccount.setBalance(
          toAccount.getBalance() + getTransactionAmoutToRate());
      accountRepository.save(toAccount);
    }
  }

  private void getCurrencyRate() throws Exception {

    RestTemplate restTemplate = new RestTemplate();

    if (getTransactionTypeEqTOrW()) {

      Float temp1 =
          ExchangeRates.getExchangeRate(BANK_DEFAULT_CURRENCY, transaction.getFromcurrency());
      transaction.setFromrate(new Float((Math.round(temp1 * 100.0) / 100.0)));

      transaction.setAmount(transaction.getAmount() / transaction.getFromrate());
    }
    if (getTransactionTypeEqT() || getTransactionTypeEqD()) {
      Float temp2 =
          ExchangeRates.getExchangeRate(BANK_DEFAULT_CURRENCY, transaction.getTocurrency());
      transaction.setTorate(new Float((Math.round(temp2 * 100.0) / 100.0)));

      if (getTransactionTypeEqD())
        transaction.setAmount(transaction.getAmount() / transaction.getTorate());
    }
  }
}
