package com.app.service;

import com.app.enties.User;

public interface UserService {
  void save(User user);

  User findByUsername(String username);
}
