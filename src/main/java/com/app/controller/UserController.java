/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.SpringUserStatic;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class UserController {
  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @RequestMapping("/api/auth")
  @GetMapping
  Map<String, Object> getToken(HttpSession session) {
    return Collections.singletonMap("session", session.getId());
  }

  @RequestMapping(value = "/api/registration")
  @PostMapping
  public String registration(@RequestBody SpringUserStatic user) {
    userService.save(user);
    return "{'status':'ok'}";
  }
}
