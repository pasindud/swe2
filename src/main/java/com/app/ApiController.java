/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pasindu
 */
@RestController
public class ApiController {

        @RequestMapping("/api/auth")
	@GetMapping
	Map<String, Object> getToken(HttpSession session) {
		return Collections.singletonMap("session", session.getId());
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
