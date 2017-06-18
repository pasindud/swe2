

package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pasindu
 */
public class AccountController {
@Autowired private AccountRepository accountRepository;

  @RequestMapping("/api/accounts")
  @GetMapping
  List<Account> getAccounts(HttpSession session) {
    // Testing.
    System.out.println(accountRepository.findAll());
    Users users = new Users();
    users.setUserId(1);
    return accountRepository.findByUserid(users);
  }
}
