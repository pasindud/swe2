package com.app.service;

import com.app.enties.LoginHistory;
import com.app.repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pasindu on 7/2/17.
 */
@Service
public class LoginHistoryService {

  @Autowired
  LoginHistoryRepository loginHistoryRepository;
  public void logUserLogginHistory(int authorized, String username) {
    LoginHistory loginHistory = new LoginHistory();
    loginHistory.setUsername(username);
    loginHistory.setAuthorized(authorized);
    loginHistoryRepository.save(loginHistory);
  }
}
