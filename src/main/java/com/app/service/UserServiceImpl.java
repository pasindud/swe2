package com.app.service;

import com.app.enties.Users;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserServiceImpl() {
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  public UserServiceImpl(UserRepository userRepository) {
    this();
    this.userRepository = userRepository;
  }

  @Override
  public void save(Users user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    //  TODO need to implement the user type /roles
    //user.setRoles(new HashSet<>(roleRepository.findAll()));

    userRepository.save(user);
  }

  @Override
  public Users findByUsername(String username) {
    // Tesitn gosmething
    return userRepository.findByUsername(username);
  }
}
