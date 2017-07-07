package com.app.service;

import com.app.annotation.CheckdbValidator;
import com.app.enties.*;
import com.app.repository.*;
import com.app.request.CreateAccountRequest;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidatorContext;

/** Created by Pasindu on 6/29/17. */
@Service
public class AccountService {
  private static final Logger logger = LoggerFactory.getLogger(CheckdbValidator.class);
  @Autowired BranchRepository branchRepository;
  @Autowired AccountTypeRepository accountTypeRepository;
  @Autowired UsersRepository usersRepository;
  @Autowired AccountRepository accountRepository;
  @Autowired UserServiceImpl userService;
  @Autowired
  MerchantRepository merchantRepository;
  private String currency;
  private int accTypeid;
  private int branchid;
  private List<String> errors;

  public List<String> createAccount(CreateAccountRequest createAccountRequest) {
    errors = new ArrayList<String>();
    this.accTypeid = createAccountRequest.getAccTypeid();
    this.branchid = createAccountRequest.getBranchid();
    this.currency = createAccountRequest.getCurrency();
    validateIds();
    if (errors.isEmpty()) {
      save();
    }
    return errors;
  }

  public boolean checkUserHasPermissions(Integer accountId, boolean userCheck) {
      if (accountId == null) {
       return false;
      }

      logger.info(String.format("Validating account id - %s", accountId));
      Account account = accountRepository.findByAccountid(accountId);
      if (account == null) {
        logger.info(String.format("Validating No account id - %s found", accountId));
        return false;
      } else if (userCheck == false) {
        logger.info(String.format("Validating account - %s exists.", accountId));
        return true;
      } else if (account.getUserId().getUserId() == userService.getLoggedInUserId()) {
        logger.info(String.format("Validating account %s owned by .", accountId, userService.getLoggedInUserId()));
        return true;
      } else {
        logger.error(String.format("User %s trying to access account %s owned by %s",
                userService.getLoggedInUserId(),
                accountId, account.getUserId().getUserId()));
        return false;
      }
  }

  private void save() {
    AccountType accountType = new AccountType();
    accountType.setAccTypeId(accTypeid);

    Branch branch = new Branch();
    branch.setBranchId(branchid);

    Users users = new Users();
    users.setUserId(userService.getLoggedInUserId());

    Account account = new Account();
    account.setCurrency(currency);
    account.setAccTypeId(accountType);
    account.setBranchId(branch);
    account.setUserId(users);

    if (accountRepository.save(account) == null) {
      errors.add("Unable to save create account.");
    }
  }

  private void validateIds() {
    if (branchRepository.findByBranchId(branchid) == null) {
      errors.add("Invalid branch");
    }

    if (accountTypeRepository.findByAccTypeId(accTypeid) == null) {
      errors.add("Invalid account type");
    }
  }
}
