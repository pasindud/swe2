package com.app.controller;

import com.app.enties.Account;
import com.app.enties.AccountType;
import com.app.repository.AccountRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    AccountType type = new AccountType();
    type.setAccName(rs.getString("acc_name"));
    user.setAccTypeId(type);
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

  @Autowired private JdbcTemplate jdbcTemplate;

  @RequestMapping(value = "/api/accounts")
  @GetMapping
  Object getAccounts(@RequestParam("id") Integer id, HttpSession session) {
    return jdbcTemplate.queryForObject(
        "SELECT *\n"
            + "FROM account \n"
            + "LEFT JOIN account_type ON account.acctypeid = account_type.acc_type_id\n"
            + "where account.accountid = ?;",
        new Object[] {id},
        new UserRowMapper());
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
