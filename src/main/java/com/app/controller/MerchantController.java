/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Merchant;
import com.app.enties.MerchantServices;
import com.app.repository.MerchantRepository;
import com.app.repository.MerchantServicesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
/*

curl -u xyz:xyz -v http://localhost:8080/api/merchant

*/

/** @author Pasindu */
@RestController
public class MerchantController {
    private String GET_ALL_MERCHANT_SERVICES_SQL_QUERY = "select merchant_services.serviceid, \n" +
"merchant_services.description,\n" +
"merchant_services.servicename,\n" +
"merchant.orgname\n" +
"from merchant_services left join `merchant` on `merchant`.`merchantid` = merchant_services.`merchantid`";
    
  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private MerchantRepository merchantRepository;
  @Autowired private MerchantServicesRepository merchantServicesRepository;

  @RequestMapping("/api/merchant")
  @GetMapping
  public List<Merchant> findAll() {
    return merchantRepository.findAll();
  }

  @RequestMapping("/api/merchant_services")
  public List<Map<String, Object>> getAllServicesByMerchantId() {
      return jdbcTemplate.queryForList(GET_ALL_MERCHANT_SERVICES_SQL_QUERY);
//    Merchant m = new Merchant();
//    m.setMerchantid(1);    
//    return merchantServicesRepository.findAllByMerchantUserid(m);
  }
}
