/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.Utils;
import com.app.enties.SecurityAnswers;
import com.app.enties.SecurityQuestions;
import com.app.repository.SecurityAnswersRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author dilsh
 */
@Service
public class SecurityAnswersService {
    @Autowired SecurityAnswersRepository securityAnswersRepository;
    @Autowired private UserServiceImpl userService;

    /**
     * Return random 2 out of 3 security questions for user
     * @param userid user id of the requesting user
     * @return 
     */
     public List<SecurityQuestions> getRandomQuestions(int userid){
        List<SecurityAnswers> allAnswesr=securityAnswersRepository.findByUserId(userid);
        List<SecurityQuestions> random_questions=new ArrayList<SecurityQuestions>();
        
        int random=Utils.randInt(1, 3);
        int currentPosition = 0;
        for(SecurityAnswers element : allAnswesr){
            currentPosition++;
            if(random!=currentPosition)
                random_questions.add(element.getSecurityQuestions());
        }
         
        return random_questions;
     }
     
     /**
      * verifies security answers with user request  
      * @param answers list of SecurityAnswers from clients
      * @param userid  User id of whom SecurityAnswers belong 
      * @return 
      */
     public boolean verifyanswers(List<SecurityAnswers> answers,int userid){
         String currentAnswer="";
         
         for(SecurityAnswers element : answers){
             currentAnswer=AnswerForQuestion(element.getSecurityQuestions().getId(),userid);
            if(!currentAnswer.equalsIgnoreCase(element.getAnswer()))
                return false;
         }
         return true;
     }
     
     /**
      * 
      * @param questionid security question id for user
      * @param userid user
      * @return the answer related to the user's security question. null if user have no answer for that question id
      */
     public String AnswerForQuestion(int questionid,int userid){
        List<SecurityAnswers> answers=securityAnswersRepository.findByUserId(userid);
        for(SecurityAnswers element : answers){            
            if(element.getSecurityQuestions().getId()==questionid)
                return element.getAnswer();
         }
        return null;
     }
    
}
