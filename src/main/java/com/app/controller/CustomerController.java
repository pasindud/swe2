/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Customer;
import com.app.enties.Users;
import com.app.repository.CustomerRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

import com.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Ishanka Ranatunga */
@RestController
public class CustomerController {
  @Autowired private CustomerRepository customerRepository;
  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private UserServiceImpl userService;
  /*
      curl -u xyz:xyz -v http://localhost:8080/api/customer?id=1
  */
  @RequestMapping("/api/customer")
  @GetMapping
  Customer getCustomers() {
    Users users = userService.getLoggedInUser();
    return customerRepository.findByCustomerid(users.getCustomer().getUserId());
  }

  @RequestMapping("/api/customer_save")
  @PostMapping
  Customer save(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }
}
