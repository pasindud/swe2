/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.request.CreateUserRequest;
import com.app.service.UserService;
import com.app.service.UserServiceImpl;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class ApiController {
  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @RequestMapping("/api/auth")
  @GetMapping
  Map<String, Object> getToken(HttpSession session) {
    return Collections.singletonMap("session", session.getId());
  }

  /*
   * Creates new user
   */
  @RequestMapping(value = "/api/createUser", method = RequestMethod.POST)
  public String createUser(@RequestBody CreateUserRequest createuserRequest) {

    User user = new User();
    user.setUsername(createuserRequest.getUsername());
    user.setPassword(createuserRequest.getPassword());
    user.setPasswordConfirm(createuserRequest.getPasswordConfirm());

    //  TODO need to implement the user type /roles
    //user.setRoles(createuserRequest.getRoles());

    UserServiceImpl userService = new UserServiceImpl(this.userRepository);
    userService.save(user);
    return "ok";
  }

  @RequestMapping(value = "/api/registration", method = RequestMethod.POST)
  public String registration(@RequestBody User user, BindingResult bindingResult, Model model) {
    System.out.println("User " + user.getUsername());
    System.out.println("User " + user.getPassword());

    userService.save(user);
    // Do this correctly next time.
    return "okok123";
  }

  @Secured("ROLE_USER")
  @RequestMapping("/api/greet")
  @GetMapping
  Map<String, Object> greet(@AuthenticationPrincipal Principal user) {
    Map<String, Object> map = new HashMap<>();
    map.put("user", user.getName());
    return map;
  }

  @RequestMapping("/api/admin")
  @GetMapping
  @Secured("ROLE_ADMIN")
  Map<String, Object> manage(@AuthenticationPrincipal Principal user) {
    return Collections.singletonMap("user", user.getName());
  }
}