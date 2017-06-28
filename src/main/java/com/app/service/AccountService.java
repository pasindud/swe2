package com.app.service;

import com.app.Utils;
import com.app.enties.Account;
import com.app.enties.AccountType;
import com.app.enties.Branch;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.AccountTypeRepository;
import com.app.repository.BranchRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasindu on 6/29/17.
 */
@Service
public class AccountService {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserServiceImpl userService;
    private String currency;
    private int accTypeid;
    private int branchid;
    private List<String> errors = new ArrayList<String>();

    public List<String> createAccount(CreateAccountRequest createAccountRequest) {
        this.accTypeid = createAccountRequest.getAccTypeid();
        this.branchid = createAccountRequest.getBranchid();
        validateIds();
        if (errors.isEmpty()) {
            save();
        }
        return errors;
    }

    private void save() {
        AccountType accountType = new AccountType();
        accountType.setAccTypeId(accTypeid);

        Branch branch = new Branch();
        branch.setBranchId(branchid);

        Users users = new Users();
        users.setUserId(userService.getLoggedInUserId());

        Account account = new Account();
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
