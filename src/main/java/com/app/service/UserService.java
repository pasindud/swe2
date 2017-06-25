package com.app.service;

import com.app.enties.Users;

public interface UserService {
  void save(Users user);

  Users findByUsername(String username);
}
