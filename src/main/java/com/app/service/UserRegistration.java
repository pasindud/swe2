package com.app.service;

import com.app.enties.Customer;
import com.app.enties.Users;
import com.app.repository.CustomerRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateUserRequest;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Created by Pasindu on 6/29/17. */
@Service
public class UserRegistration {
  private static final Logger logger = LoggerFactory.getLogger(UserRegistration.class);
  @Autowired UserServiceImpl userService;
  @Autowired UsersRepository usersRepository;
  @Autowired CustomerRepository customerRepository;
  private Users users;
  private Customer customer;
  private List<String> errors;

  public List<String> registerUser(CreateUserRequest createUserRequest) {
    errors = new ArrayList<String>();
    try {
      this.users = createUserRequest.getUsers();
      this.customer = createUserRequest.getCustomer();
      validateUserRegistration();
      if (errors.isEmpty()) {
        save();
      }
    } catch (Exception e) {
      errors.add("Unknown error occured");
      logger.error("Error occurred while registering user", e);
    }
    return errors;
  }

  private void save() {
    users.setCustomer(customer);
//    Users newUser = usersRepository.save(users);
    Users newUser = userService.save(users);
    if (newUser == null) {
      errors.add("Cannot create users");
    }
  }

  private void validateUserRegistration() {
    if (userService.findByUsername(users.getUsername()) != null) {
      errors.add("User name already exists");
    }
  }
}
