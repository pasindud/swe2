/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.repository.LoginHistoryRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateUserRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import com.app.service.UserRegistration;
import com.app.service.UserServiceImpl;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pasindu
 */
@RestController
public class UserController {
    
    private static String ACCESS_LEVEL_ID = "1";
    private static String RESPONSE_SESSION_MAP_KEY = "session";
    private static String RESPONSE_ACCESS_LEVEL_MAP_KEY = "AccessLevel";
    private static String RESPONSE_USERID_MAP_KEY = "userId";
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private UserRegistration userRegistration;

    @RequestMapping("/api/auth")
    @GetMapping
    Map<String, Object> getToken(HttpSession session) {
        userService.userLoggedIn();
        HashMap<String, Object> map = new HashMap<>();
        map.put(RESPONSE_SESSION_MAP_KEY, session.getId());
        map.put(RESPONSE_USERID_MAP_KEY, userService.getLoggedInUserId());
        map.put(RESPONSE_ACCESS_LEVEL_MAP_KEY, ACCESS_LEVEL_ID);
        return map;
    }

    /*

  curl -H "Content-Type: application/json" -X POST \
  -d '{"username":"xyz","password":"xyz"}¡¡™' \
  http://localhost:8080/api/registration
     */
    @RequestMapping(value = "/api/registration")
    @PostMapping
    public ImmutableMap<String, List<String>> registration(@RequestBody CreateUserRequest createUserRequest) {
        return ImmutableMap.of("errors", userRegistration.registerUser(createUserRequest));
    }
}
