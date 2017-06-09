package com.app;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
