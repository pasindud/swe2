package com.app.controller;

import static com.app.Utils.FreqAmount;
import com.app.Utils;
import com.app.enties.Account;
import com.app.enties.Customer;
import com.app.enties.Merchant;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.MerchantRepository;
import com.app.repository.TransactionRepository;
import com.app.repository.UsersRepository;
import com.app.service.AdminService;
import com.app.service.ExchangeRates;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.rmi.CORBA.Util;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Class used for API endpoints for admin operations.
 */
@RestController
public class AdminController {
  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
  /** Repository for accessing user data. */
  @Autowired UsersRepository usersRepository;
  @Autowired AccountRepository accountRepository;
  @Autowired MerchantRepository merchantRepository;
  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired TransactionRepository transactionRepository;
  @Autowired
  AdminService adminService;

  /**
   * <p>
   *  curl -u ADMIN:xyz "http://localhost:8080/api/admin/get_freq_amount"
   * <p/>
   */
  @GetMapping("/api/admin/get_freq_amount")
  public void getFreqAmount() {
    double amount = adminService.analyzeAmounts();
  }

  /**
   * API endpoint to get list of accounts by the given user.
   * @param userId the id of user to filter the accounts from
   * @return list of accounts owned by the given userid.
   */
  @GetMapping("/api/admin/account__by_user_id")
  public List<Account> getAccountsByUsers(@RequestParam("userid") int userId) {
    Users user = new Users();
    user.setUserId(userId);
    return accountRepository.findByUserid(user);
  }

  /**
   * Activate and deactivate users.
   * @param userId the id of user to toggle status of.
   * @param lock whether status should be active or in active.
   * @return whether the operation was successful or not,
   */
  @GetMapping("/api/admin/change_user_status")
  public Map<String, String> changeUserStatus(@RequestParam("user_id") String userId, @RequestParam("lock") boolean lock) {
    usersRepository.updateLock(lock, Integer.parseInt(userId));
    return ImmutableMap.of("error", "");
  }

  /**
   * API endpoint to lock accounts.
   * <p>
   *   curl -u ADMIN:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=true"
   * <p/>
   * @param accountid the id of the account to be locked or unlocked.
   * @param lock whether to lock or unlock the account.
   * @return the updated state of account.
   */
  @GetMapping("/api/admin/lock_account")
  public Map<String, String> lockAccount(@RequestParam("accountid") int accountid, @RequestParam("lock") boolean lock) {
//    return accountRepository.updateAccountLock(lock, accountid);
    Map<String, String> response = new HashMap<>();
    if (accountRepository.updateAccountLock(lock, accountid) == 1) {
      if (lock) {
        response.put("data", "Account locked");
      } else {
        response.put("data", "Account unlocked");
      }
      return response;
    } else {
      response.put("errors", "Error toggling acount lock");
      return response;
    }
  }

  /**
   * API endpoint to get all the accounts.
   * @return
   */
  @GetMapping("/api/admin/all_acounts")
  public List<Account> getAccounts(){
    return accountRepository.findAll();
  }

  @GetMapping("/api/admin/user_by_id")
  public Users getUserInfoById(@RequestParam("id") int id) {
    return usersRepository.findByUserId(id);
  }

  class ResponseAccount{
    public Account account;
    public Users user;
  }

  /**
   * API endpoint get all details of the given account.
   * @param id of the account to get the details of.
   */
  @GetMapping("/api/admin/account_id")
  public Object getAccountById(@RequestParam("id") int id) {
    Map<String, String> response = new HashMap<>();
    Account account = accountRepository.findByAccountid(id);

    if (account == null) {
      response.put("errors", "Not account found");
      return response;
    }

    ResponseAccount responseAccount = new ResponseAccount();
    responseAccount.account = account;
    responseAccount.user = account.getUserId();

    Users users = new Users();
    users.setUserId(account.getUserId().getUserId());

    account.setUserId(account.getUserId());

    return responseAccount;
  }

  /**
   * API endpoint get all the users.
   * @return
   */
  @RequestMapping("/api/admin/all_users")
  public List<Users> getUsers() {
    return usersRepository.getAllUsers();
  }

  /**
   * API endpoint to get all the merchants.
   * @return
   */
  @RequestMapping("/api/admin/all_merchants")
  public List<Merchant> getMerchants() {
    return merchantRepository.findAll();
  }
}
