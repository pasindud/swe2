/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Users;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class UserController {
  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @RequestMapping("/api/auth")
  @GetMapping
  Map<String, Object> getToken(HttpSession session) {
    String accessLevelID = "1"; //user role Id
    HashMap<String, Object> map = new HashMap<>();
    map.put("session", session.getId());
    
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername(); //get logged in username
    System.out.println(user.getUsername());

    Users loggedInUser = userRepository.findByUsername(name);
    map.put("userId", loggedInUser.getUserId());
    map.put("AccessLevel", accessLevelID);
    return map;
  }

  /*

  curl -H "Content-Type: application/json" -X POST \
  -d '{"username":"xyz","password":"xyz"}' \
  http://localhost:8080/api/registration
  */
  @RequestMapping(value = "/api/registration")
  @PostMapping
  public String registration(@RequestBody Users user) {
    userService.save(user);
    return "{'status':'ok'}";
  }
}
