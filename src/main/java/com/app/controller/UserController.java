/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Role;
import com.app.enties.SecurityAnswers;
import com.app.enties.SecurityQuestions;
import com.app.enties.Users;
import com.app.repository.LoginHistoryRepository;
import com.app.repository.SecurityAnswersRepository;
import com.app.repository.SecurityQuestionRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateUserRequest;
import com.app.service.SecurityAnswersService;
import com.app.service.UserRegistration;
import com.app.service.UserServiceImpl;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class UserController {

  private static String CUSTOMER_ACCESS_LEVEL_ID = "1";
  private static String ADMIN_ACCESS_LEVEL_ID = "3";
  private static String RESPONSE_SESSION_MAP_KEY = "session";
  private static String RESPONSE_ACCESS_LEVEL_MAP_KEY = "AccessLevel";
  private static String RESPONSE_USERID_MAP_KEY = "userId";

  @Autowired private UserServiceImpl userService;

  @Autowired private UsersRepository usersRepository;

  @Autowired private LoginHistoryRepository loginHistoryRepository;

  @Autowired private SecurityAnswersRepository securityAnswersRepository;

  @Autowired private SecurityQuestionRepository securityQuestionRepository;

  @Autowired private UserRegistration userRegistration;

  @Autowired private SecurityAnswersService securityAnswersService;
  
  @RequestMapping("/api/auth")
  @GetMapping
  Map<String, Object> getToken(HttpSession session) {
    Users user = userService.getLoggedInUser();
    HashMap<String, Object> map = new HashMap<>();
    map.put(RESPONSE_SESSION_MAP_KEY, session.getId());
    map.put(RESPONSE_USERID_MAP_KEY, userService.getLoggedInUserId());

    List<Role> list = new ArrayList<Role>(user.getRoles());
    
    if (list.get(0).getName().equals("ADMIN")) {
      map.put(RESPONSE_ACCESS_LEVEL_MAP_KEY, ADMIN_ACCESS_LEVEL_ID);
    } else {
      map.put(RESPONSE_ACCESS_LEVEL_MAP_KEY, CUSTOMER_ACCESS_LEVEL_ID);
    }
    return map;
  }

  /*
    curl -u xyz:xzy "http://localhost:8080/api/user_questions?userName=aaabbbccc"
  */
  @RequestMapping("/api/user_questions")
  @GetMapping
  public List<SecurityQuestions> getSecurityQuestions(@RequestParam("userName") String userName ) {
      Users user=userService.findByUsername(userName);
      if (user!=null)
        return securityAnswersService.getRandomQuestions(user.getUserId());//securityAnswersRepository.findByUserId(userService.getLoggedInUser().getUserId());
      return  null;
  }
  
@RequestMapping("/api/security_answer_vrification")
@PostMapping
  public boolean verifyAnserwers(@RequestBody List<SecurityAnswers> answers,@RequestParam("userName") String userName){
     Users user=userService.findByUsername(userName);
      if (user!=null)
        return securityAnswersService.verifyanswers(answers,user.getUserId());
     return  false;
  }
  /*

  curl -H "Content-Type: application/json" -X POST \
  -d '{"username":"xyz","password":"xyz"}¡¡™' \
  http://localhost:8080/api/registration
     */
  @RequestMapping(value = "/api/registration")
  @PostMapping
  public ImmutableMap<String, List<String>> registration(
      @RequestBody CreateUserRequest createUserRequest) {

    return ImmutableMap.of("errors", userRegistration.registerUser(createUserRequest));
  }
}
