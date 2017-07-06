/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.crypt.CustomerCryptor;
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

  private class CustomerRowMapper implements RowMapper
  {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      Customer customer = new Customer();
      customer.setFirstName(rs.getString("first_name"));
      customer.setLastName(rs.getString("last_name"));
      customer.setEmail(rs.getString("email"));
      customer.setAddressLine1(rs.getString("address_line1"));
      customer.setAddressLine2(rs.getString("address_line2"));
      customer.setAddressLine3(rs.getString("address_line3"));
      customer.setGender(rs.getString("gender"));
      customer.setMobileNo(rs.getString("mobile_no"));
      customer.setTelephoneNo(rs.getString("telephone_no"));
      customer.setTitle(rs.getString("title"));
      customer.setFaxNo(rs.getString("fax_no"));
      customer.setDob(rs.getString("dob"));
      customer.setCity(rs.getString("city"));
      return customer;
    }

  }

  @RequestMapping("/api/customer")
  @GetMapping
  Customer getCustomers() {
    Users users = userService.getLoggedInUser();

    String sql = "Select * FROM customer  c where c.customerid=?";

    Customer customer = (Customer)jdbcTemplate.queryForObject(
            sql, new Object[] {  users.getCustomer().getUserId() }, new CustomerRowMapper());

    CustomerCryptor customerCryptor = new CustomerCryptor();
    // Remove after unlimited issue is solved.
    // Customer decustomer = customerCryptor.decodeCustomer(users.getUsername(),users.getPassword(), customer);
    return customer;
  }

  @RequestMapping("/api/customer_save")
  @PostMapping
  Customer save(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }
}
