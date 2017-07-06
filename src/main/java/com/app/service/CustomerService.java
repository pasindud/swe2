/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.enties.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Map;
/**
 *
 * @author dilsh
 */
@Service
public class CustomerService {
    public List<Customer> findAll(){
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<Customer>();
        
//        List<Map> rows = getJdbcTemplate().queryForList(sql);
//	for (Map row : rows) {
//		Customer customer = new Customer();
//		customer.setCustId((Long)(row.get("CUST_ID")));
//		customer.setName((String)row.get("NAME"));
//		customer.setAge((Integer)row.get("AGE"));
//		customers.add(customer);
//	}

	return customers;
    }
}
