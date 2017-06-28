/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.*;
import com.app.enties.Merchant;
import com.app.enties.MerchantServices;
import com.app.enties.Transaction;
import com.app.repository.MerchantRepository;
import com.app.repository.MerchantServicesRepository;
import com.app.request.PayBillRequest;
import com.app.service.TransactionService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*

curl -u xyz:xyz -v http://localhost:8080/api/merchant

 */

/**
 * @author Pasindu
 */
@RestController
public class MerchantController {

    private String GET_ALL_MERCHANT_SERVICES_SQL_QUERY = "select merchant_services.serviceid, \n"
            + "merchant_services.description,\n"
            + "merchant_services.servicename,\n"
            + "merchant.orgname\n"
            + "from merchant_services left join `merchant` on `merchant`.`merchantid` = merchant_services.`merchantid`";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private MerchantServicesRepository merchantServicesRepository;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/api/merchant")
    @GetMapping
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @RequestMapping("/api/merchant_services")
    public List<Map<String, Object>> getAllServicesByMerchantId() {
        return jdbcTemplate.queryForList(GET_ALL_MERCHANT_SERVICES_SQL_QUERY);
    }

    /**
     *
     *
     * curl -u xyz:xyz -H "Content-Type: application/json" -X POST \
     * http://localhost:8080/api/merchant_services_pay_bill \ -d '{"amount":1,
     * "selectedServiceId": 1, "selectedAccountId":1, "billReferenceNumber": 1}'
     *
     */
    @RequestMapping("/api/merchant_services_pay_bill")
    @PostMapping
    public Map<String, List<String>> payBill(@RequestBody PayBillRequest payBillRequest) {
        Transaction transaction = new Transaction();
        MerchantServices service
                = merchantServicesRepository.findByServiceid(payBillRequest.getSelectedServiceId());

        if (service == null) {
        }
        // Validate the code here.
        transaction.setFromaccountid(payBillRequest.getSelectedAccountId());
        transaction.setToaccountid(service.getAccountid());
        System.out.println("payBillRequest.getAmount() " + payBillRequest.getAmount());
        transaction.setAmount(payBillRequest.getAmount());

        List<String> errors = transactionService.do_transactions(transaction);

        Map<String, List<String>> response = new HashMap<String, List<String>>();
        response.put("errors", errors);
        return response;
    }
}
