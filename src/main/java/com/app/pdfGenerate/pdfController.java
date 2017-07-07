/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.pdfGenerate;

import com.app.enties.Transaction;
import com.app.service.TransactionService;
import com.app.repository.TransactionRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ishanka Ranatunga
 */
@RestController
public class pdfController {
    
    private int accountID;
    private Date fromDate;

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    @Autowired
    TransactionService tService;
    TransactionRepository tRespo;
    
    @RequestMapping(value = "/api/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> transactionsReport() throws IOException {

        List<Transaction> transactions = (List<Transaction>) tRespo.getAccountTransactions(accountID);

        ByteArrayInputStream bis = GeneratePdfReport.transactionReport(transactions);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=transactionsreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
