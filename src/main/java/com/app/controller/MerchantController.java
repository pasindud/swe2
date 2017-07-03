/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.Utils;
import com.app.enties.Merchant;
import com.app.enties.MerchantServices;
import com.app.enties.Transaction;
import com.app.enties.TransactionType;
import com.app.repository.MerchantRepository;
import com.app.repository.MerchantServicesRepository;
import com.app.request.PayBillRequest;
import com.app.service.TransactionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class for merchant API endpoints.
 */
@RestController
public class MerchantController {
  /** Query to get list of merchant services. */
  private String GET_ALL_MERCHANT_SERVICES_SQL_QUERY =
      "select merchant_services.serviceid, \n"
          + "merchant_services.description,\n"
          + "merchant_services.servicename,\n"
          + "merchant.orgname\n"
          + "from merchant_services left join `merchant` on `merchant`.`merchantid` = merchant_services.`merchantid`";
  /** Class used to query data. */
  @Autowired private JdbcTemplate jdbcTemplate;
  /** Repository to access merchant data. */
  @Autowired private MerchantRepository merchantRepository;
  /** Repository to access merchant service data. */
  @Autowired private MerchantServicesRepository merchantServicesRepository;
  /** Service to operate on transaction data. */
  @Autowired private TransactionService transactionService;
  @Autowired UserServiceImpl userService;

  /**
   * API to get all the merchants.
   * @return list of merchants.
   */
  @RequestMapping("/api/merchant")
  @GetMapping
  public List<Merchant> findAll() {
    return merchantRepository.findAll();
  }

  /**
   * API to get all the merchants and thier services.
   * @return list of merchants services.
   */
  @RequestMapping("/api/merchant_services")
  public List<Map<String, Object>> getAllServicesByMerchantId() {
    return jdbcTemplate.queryForList(GET_ALL_MERCHANT_SERVICES_SQL_QUERY);
  }

  /**
   * API endpoint to create a new merchant.
   * @return the newly create merchant.
   */
  @RequestMapping("/api/create_merchant")
  public Object createMerchant() {
    return null;
  }

  /**
   * API endpoint to create a new merchant service.
   * @return the newly create merchant service.
   */
  @RequestMapping("/api/create_merchant_service")
  public Object createMerchantService() {
    return null;
  }

  /**
   * API endpoint to pay bills.
   * @param payBillRequest the API request contain details required data for the operation.
   * @param requestError errors in the API request.
   * @return if returned error map is empty then the operation was successful else errors can be found in the map.
   */
  @RequestMapping("/api/merchant_services_pay_bill")
  @PostMapping
  public Map<String, List<String>> payBill(
      @Valid @RequestBody PayBillRequest payBillRequest, Errors requestError) {
    Map<String, List<String>> response = new HashMap<String, List<String>>();

    if (requestError.hasErrors()) {
      response.put("errors", Utils.getListFromErrors(requestError));
      return response;
    }

    Transaction transaction = new Transaction();
    MerchantServices service =
        merchantServicesRepository.findByServiceid(payBillRequest.getSelectedServiceId());

    if (service == null) {}
    transaction.setTranstype(TransactionType.T);
    // Validate the code here.
    transaction.setFromaccountid(payBillRequest.getSelectedAccountId());
    transaction.setUserId(userService.getLoggedInUser());
    transaction.setToaccountid(service.getAccountid());
    System.out.println("payBillRequest.getAmount() " + payBillRequest.getAmount());
    transaction.setAmount(payBillRequest.getAmount());

    List<String> errors = new ArrayList <>();
    try {
      errors = transactionService.do_transactions(transaction);
    } catch (Exception e) {
      e.printStackTrace();
      errors.add("Error occured while trying to pay the bills.");
    }

    response.put("errors", errors);
    return response;
  }
}
