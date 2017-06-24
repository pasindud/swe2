package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
curl -u xyz:xyz -v http://localhost:8080/api/accounts?id=1
*/
/** @author Pasindu */
@RestController
public class AccountController {
  @Autowired private AccountRepository accountRepository;

  @RequestMapping("/api/accounts")
  @GetMapping
  List<Account> getAccounts(@RequestParam("id") Integer id, HttpSession session) {
    Users users = new Users();
    users.setUserId(id);
    return accountRepository.findByUserid(users);
  }
  
  /*
    curl -u xyz:xyz  -H "Content-Type: application/json" \
    http://localhost:8080/api/accounts_save \
    -d '{"balance":1, "accTypeId": {"accTypeId":1}, "branchId": {"branchId":1, "addressLine1":"TESTING"}, "userId":{"userId":1}}'
  
  */
  @RequestMapping("/api/accounts_save")
  @PostMapping
  Account save(@RequestBody Account account) {
    return accountRepository.save(account);
  }
}
