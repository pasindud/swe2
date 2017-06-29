package com.app.service;

import com.app.Utils;
import com.app.enties.LoginHistory;
import com.app.enties.Users;
import com.app.repository.LoginHistoryRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
  @Autowired private SecurityServiceImpl securityService;
  @Autowired private UsersRepository usersRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private LoginHistoryRepository loginHistoryRepository;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserServiceImpl() {
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  public int getLoggedInUserId() {
    return usersRepository.findByUsername(Utils.getCurrentUsers()).getUserId();
  }

  public UserServiceImpl(UsersRepository usersRepository) {
    this();
    this.usersRepository = usersRepository;
  }

  public Users save(Users user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return usersRepository.save(user);
  }

  public void userLoggedIn() {
    Users users = new Users();
    users.setUserId(getLoggedInUserId());
    LoginHistory loginHistory = new LoginHistory();
    loginHistory.setUserid(users);
    loginHistoryRepository.save(loginHistory);
  }

  public Users findByUsername(String username) {
    return usersRepository.findByUsername(username);
  }
}
