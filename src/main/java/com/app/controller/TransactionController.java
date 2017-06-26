

package com.app.controller;

import com.app.enties.Account;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Pasindu */
@RestController
public class TransactionController {
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private AccountRepository accountRepository;

  /*

    curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
    http://localhost:8080/api/do_transaction \
    -d '{"toaccountid":1, "fromaccountid": 1}'

  */
  @RequestMapping("/api/do_transaction")
  @PostMapping
  private Object doTransaction(@RequestBody Transaction fromTransaction) {

    Account fromAccount = accountRepository.findByAccountid(fromTransaction.getFromaccountid());

    if (fromAccount == null) {
      return "Error no from account found";
    }

    Account toAccount = accountRepository.findByAccountid(fromTransaction.getToaccountid());

    if (toAccount == null) {
      return "Error no to account found";
    }

    //Currenccy
    //set default currency to LKR if null
    if(fromAccount.getCurrency().isEmpty()){
        fromAccount.setCurrency("LKR");
    }
    if(toAccount.getCurrency().isEmpty()){
        toAccount.setCurrency("LKR");
    }
    
    //Check if there is sufficient funds
    if (fromTransaction.getAmount()<=fromAccount.getBalance()){
    
        Float To_amount=new Float(0.0);
        if(toAccount.getCurrency()!=fromAccount.getCurrency()){
           
            //Todo implemet currency rate logic
            
           /*
            * Logic 
                To_amount= (fromTransaction.getAmount()/from_curr_to_USD_exRate)*USD_to_to_curr_exRate;
            */  
            //for now 
            To_amount=fromTransaction.getAmount();
            
            
        }else{
            To_amount=fromTransaction.getAmount();
        }   
    
        //Credit amount from sender
        fromTransaction.setTranstype("C");
        fromTransaction.setAmount(-fromTransaction.getAmount());
    
    
        //Record to show reciver was debited
        Transaction toTransaction=new Transaction(fromTransaction);
        toTransaction.setTranstype("D");
        toTransaction.setAmount(To_amount);

        //Save transactions
        transactionRepository.save(fromTransaction);
        transactionRepository.save(toTransaction);

        //Update Account balances
        fromAccount.setBalance(fromAccount.getBalance()+fromTransaction.getAmount());
        toAccount.setBalance(toAccount.getBalance()+toTransaction.getAmount());
        //Save Account balances

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    
    }
    else{
        return "Low balace to process the transaction";
    }
    return "Success";
  }
}
