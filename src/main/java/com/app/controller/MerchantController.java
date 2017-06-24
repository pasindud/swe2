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

/*
curl -u xyz:xyz -v http://localhost:8080/api/merchant
*/

/** @author Pasindu */
@RestController
public class MerchantController {
  @Autowired private MerchantRepository merchantRepository;
  @Autowired private MerchantServicesRepository merchantServicesRepository;
  
  @RequestMapping("/api/merchant")
  @GetMapping
  public List<Merchant> findAll() {
    return merchantRepository.findAll();
  }
  
  @RequestMapping("/api/merchant_services")
  public List<MerchantServices> getAllServicesByMerchantId() {
      Merchant m = new Merchant();
      m.setUserId(1);
      return merchantServicesRepository.findAllByMerchantuserId(m);
  }
}
