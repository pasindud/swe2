/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.enties;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author dilsh
 */
public class CustomerRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer=new Customer();
        
        customer.setCustomerid(rs.getInt("customerid"));
        customer.setTitle(rs.getString("title"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setDob(rs.getString("dob"));
        customer.setNic(rs.getString("nic"));
        customer.setGender(rs.getString("gender"));
        customer.setTelephoneNo(rs.getString("telephone_no"));
        customer.setMobileNo(rs.getString("mobile_no"));
        customer.setEmail(rs.getString("email"));
        customer.setFaxNo(rs.getString("fax_no"));
        customer.setAddressLine1(rs.getString("address_line1"));
        customer.setAddressLine2(rs.getString("address_line2"));
        customer.setAddressLine3(rs.getString("address_line3"));
        customer.setCity(rs.getString("city"));
        
        return customer;
    }
    
}
