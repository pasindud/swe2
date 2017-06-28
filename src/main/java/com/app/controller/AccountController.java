package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.UsersRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.app.request.CreateAccountRequest;
import com.app.service.AccountService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class UserRowMapper implements RowMapper {

  @Override
  public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
    Account user = new Account();
    user.setAccountid(rs.getInt("accountid"));
    return user;
  }
}

/*
curl -u xyz:xyz -v http://localhost:8080/api/accounts?id=1
*/
/** @author Pasindu */
@RestController
public class AccountController {
  @Autowired private AccountRepository accountRepository;

  @Autowired private UsersRepository usersRepository;
  @Autowired private AccountService accountService;
  @Autowired private JdbcTemplate jdbcTemplate;

  Object getOneAccount(@RequestParam("id") Integer id) {
    return jdbcTemplate.queryForObject(
        "select * from account where accountid=?", new Object[] {id}, new UserRowMapper());
  }

  @RequestMapping("/api/accounts_user")
  @GetMapping
  Object getAccountsByUserId(@RequestParam("userid") Integer id, HttpSession session) {
    Users users = new Users();
    users.setUserId(id);
    return accountRepository.findByUserid(users);
  }
  
  @RequestMapping("/api/accounts")
  @GetMapping
  Object getAccounts(@RequestParam("id") Integer id, HttpSession session) {
    Users users = new Users();
    users.setUserId(id);

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername(); //get logged in username
    System.out.println(user.getUsername());

    Users loggedInUser = usersRepository.findByUsername(name);

    List<Account> accounts = accountRepository.findByUserid(users);
    for (int i = 0; i < accounts.size(); i++) {
      if (loggedInUser.getUserId() != accounts.get(i).getUserId().getUserId()) {
        System.out.println("Users does not have.");
      }
    }
    return accounts;
  }

  /*

    curl -u xyz:xyz  -H "Content-Type: application/json" \
    http://localhost:8080/api/accounts_save \
    -d '{"accTypeid":1, "currency":"LKR", "branchid":1}'

 */
  @RequestMapping("/api/accounts_save")
  @PostMapping
  ImmutableMap<String, List<String>> save(@RequestBody CreateAccountRequest createAccountRequest) {
    return ImmutableMap.of("errors", accountService.createAccount(createAccountRequest));
  }
}
