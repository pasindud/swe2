package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Merchant;
import com.app.enties.Users;
import com.app.repository.AccountRepository;
import com.app.repository.MerchantRepository;
import com.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used for API endpoints for admin operations.
 */
@RestController
public class AdminController {
  /** Repository for accessing user data. */
  @Autowired UsersRepository usersRepository;
  @Autowired AccountRepository accountRepository;
  @Autowired MerchantRepository merchantRepository;

  /**
   * Activate and deactivate users.
   * @param userId the id of user to toggle status of.
   * @param activate whether status should be active or in active.
   * @return whether the operation was successful or not,
   */
  @RequestMapping("/api/admin/change_user_status")
  public boolean changeUserStatus(@RequestParam("user_id") int userId, @RequestParam("activate") boolean activate) {
    usersRepository.updateActivate(activate, userId);
    return false;
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
  @RequestMapping("/api/admin/lock_account")
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
  @RequestMapping("/api/admin/all_acounts")
  public List<Account> getAccounts(){
    return accountRepository.findAll();
  }

  /**
   * API endpoint get all the users.
   * @return
   */
  @RequestMapping("/api/admin/all_users")
  public List<Users> getUsers() {
    return usersRepository.findAll();
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
