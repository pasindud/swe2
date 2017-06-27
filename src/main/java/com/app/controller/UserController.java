/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.Utils;
import com.app.enties.Users;
import com.app.repository.UserRepository;
import com.app.request.CreateUserRequest;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/auth")
    @GetMapping
    Map<String, Object> getToken(HttpSession session) {
        Users loggedInUser = userRepository.findByUsername(Utils.getCurrentUsers());
        HashMap<String, Object> map = new HashMap<>();
        map.put(RESPONSE_SESSION_MAP_KEY, session.getId());
        map.put(RESPONSE_USERID_MAP_KEY, loggedInUser.getUserId());
        map.put(RESPONSE_ACCESS_LEVEL_MAP_KEY, ACCESS_LEVEL_ID);
        return map;
    }

    /*

  curl -H "Content-Type: application/json" -X POST \
  -d '{"username":"xyz","password":"xyz"}' \
  http://localhost:8080/api/registration
     */
    @RequestMapping(value = "/api/registration")
    @PostMapping
    public String registration(@RequestBody CreateUserRequest createUserRequest) {
        userService.save(createUserRequest.getUser());
        return "{'status':'ok'}";
    }
}
