package com.app.controller;

import com.app.Utils;
import com.app.enties.Account;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateAccountRequest;
import com.app.service.AccountService;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class for account API endpoints.
 * */
@RestController
public class AccountController {
  /** Repository for accessing account data. */
  @Autowired private AccountRepository accountRepository;
  /** Repository for accessing user data. */
  @Autowired private UsersRepository usersRepository;
  /** Service used to operate on accounts. */
  @Autowired private AccountService accountService;

  /**
   * API endpoint to get the list of accounts for the logged in user.
   *
   * <p> Example curl command -
   * curl -u xyz:xyz "http://localhost:8080/api/accounts?id=1"
   * <p/>
   * @return list of accounts and its details.
   */
  @RequestMapping("/api/accounts")
  @GetMapping
  List<Account> getAccounts() {
    Users loggedInUser = usersRepository.findByUsername(Utils.getCurrentUsers());
    Users users = new Users();
    users.setUserId(loggedInUser.getUserId());
    return accountRepository.findByUserid(users);
  }

  /**
   * Endpoint for saving new accounts.
   *
   * <p> Example curl command -
   * curl -u xyz:xyz  -H "Content-Type: application/json" http://localhost:8080/api/accounts_save \
   *  -d '{"accTypeid":1, "currency":"LKR", "branchid":1}'
   * </p>
   */
  @RequestMapping("/api/accounts_save")
  @PostMapping
  ImmutableMap<String, List<String>> save(@RequestBody CreateAccountRequest createAccountRequest) {
    return ImmutableMap.of("errors", accountService.createAccount(createAccountRequest));
  }
}
