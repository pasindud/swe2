package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class AccountController {
  @Autowired private AccountRepository accountRepository;

  @RequestMapping("/api/accounts")
  @GetMapping
  List<Account> getAccounts(@RequestParam("id") int id, HttpSession session) {
    Users users = new Users();
    users.setUserId(id);
    return accountRepository.findByUserid(users);
  }
}
