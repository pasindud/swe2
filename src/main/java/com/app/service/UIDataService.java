/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author dilsh
 */
@Service
public class UIDataService {

  @Autowired private JdbcTemplate jdbcTemplate;
  
  
    private String GET_BRANCH_ID_NAMES =
      "SELECT `branch`.`branch_id`,`branch`.`branch_name` FROM `branch`";
    
    private String GET_ACCOUNT_TYPE_ID_NAMES =
      "SELECT `account_type`.`acc_type_id`,`account_type`.`acc_name` FROM `account_type`";    
    
    private String GET_QUESTION_ID_DESCRIPTION =
      "SELECT `security_questions`.`id`,`security_questions`.`question` FROM `security_questions`";      
    
    public List<Map<String, Object>> getBranches(){
        return  jdbcTemplate.queryForList(GET_BRANCH_ID_NAMES);
    }
    
    public List<Map<String, Object>> getAccountTypes(){
        return  jdbcTemplate.queryForList(GET_ACCOUNT_TYPE_ID_NAMES);
    }
    
    public List<Map<String, Object>> getAllSecurityQuestions(){
        return  jdbcTemplate.queryForList(GET_QUESTION_ID_DESCRIPTION);
    }
}
