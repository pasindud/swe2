package com.app.service;

import com.app.enties.SpringUserStatic;

public interface UserService {
  void save(SpringUserStatic user);

  SpringUserStatic findByUsername(String username);
}
