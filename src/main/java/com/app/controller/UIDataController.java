/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.service.UIDataService;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author dilsh
 */
@RestController
public class UIDataController {
      @Autowired private UIDataService uiDataService;

/**
 *  Returns all branches with their Id's
 *  Used to populate UI
 */
      
@RequestMapping("/api/ui_data_branch")
@GetMapping
public List<Map<String, Object>> getBranches() { 
    return uiDataService.getBranches();
  }
/**
 *  Returns all Account types with their Id's
 *  Used to populate UI
 */
@RequestMapping("/api/ui_data_acctype")
@GetMapping
public List<Map<String, Object>> getAccountType() { 
    return uiDataService.getAccountTypes();
  }
/**
 *  Returns all available security questions with their Id's
 *  Used to populate UI
 */
@RequestMapping("/api/ui_all_questions")
@GetMapping
public List<Map<String, Object>> getAllQuestions() { 
    return uiDataService.getAllSecurityQuestions();
  }
/**
 *  Returns all available security questions,Account types,branches with their Id's @ once
 *  Used to populate UI
 */
@RequestMapping("/api/ui_data")
@GetMapping
public List< Map<String, List<Map<String, Object>>>> getAllUIData() {
    List< Map<String, List<Map<String, Object>>>> data = new ArrayList<Map<String, List<Map<String, Object>>>>();
    Map<String, List<Map<String, Object>>> data_in=new HashMap<>();
    data_in.put("Branch", uiDataService.getBranches());
    data_in.put("AccountType", uiDataService.getAccountTypes());
    data_in.put("Questions", uiDataService.getAllSecurityQuestions());
    data.add(data_in);
    return data;
  }
}
