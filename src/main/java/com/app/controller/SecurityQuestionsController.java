/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.enties.Merchant;
import com.app.enties.SecurityAnswers;
import com.app.enties.SecurityQuestions;
import com.app.repository.SecurityAnswersRepository;
import com.app.repository.SecurityQuestionRepository;
import com.app.service.AccountService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dilsh
 */
@RestController
public class SecurityQuestionsController {

    @Autowired SecurityQuestionRepository securityQuestionRepository;    
    @Autowired SecurityAnswersRepository securityAnswersRepository;     
    @Autowired private AccountService accountService;
    
   /**
   * API to get all security questions .
   * @return list of merchants.
   */
  @RequestMapping("/api/security_question_all")
  @GetMapping
  public List<SecurityQuestions> findAll() {
    return securityQuestionRepository.findAll();
  }

   /**
   * API to get security questions for userId.
   * @return list of merchants.
   */
  @RequestMapping("/api/security_question")
  @GetMapping
  public List<SecurityQuestions> findQuestionForAccount(@RequestParam("id") int userId) {
    
      List<SecurityAnswers> securityAnswers = securityAnswersRepository.findByUserId(userId);
      List<SecurityQuestions> securityQuestions= new ArrayList<SecurityQuestions>();
      for (SecurityAnswers element : securityAnswers) {
          ;
          securityQuestions.add(securityQuestionRepository.findById(element.getSecurityQuestions().getId()));
      }
      return securityQuestions;
  }  
  
    
}
