/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Merchant;
import com.app.repository.MerchantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class MerchantController {
  @Autowired private MerchantRepository merchantRepository;

  @RequestMapping("/api/merchant")
  @GetMapping
  public List<Merchant> findAll() {
    return merchantRepository.findAll();
  }
}
