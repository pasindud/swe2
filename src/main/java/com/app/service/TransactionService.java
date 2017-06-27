

package com.app.service;

import com.app.enties.Account;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pasindu
 */
@Service
public class TransactionService {
    @Autowired private AccountRepository accountRepository;
    @Autowired private TransactionRepository transactionRepository;   
    
    private Transaction transaction;
    private List<String> errors = new ArrayList<String>();
    private Account fromAccount;
    private Account toAccount;
    
    private final String BANK_DEFAULT_CURRENCY="LKR";
    
    public TransactionService() {
        
    }
    
    public List<String> do_transactions(Transaction transaction) {
        errors = new ArrayList<String>();
        this.transaction = transaction;
        this.fromAccount=accountRepository.findByAccountid(transaction.getFromaccountid());
        this.toAccount=accountRepository.findByAccountid(transaction.getToaccountid());
        
        if(validate()){
            saveTransaction();
            updateAccounts();
        }
        
        return errors;
    }
    
    private boolean validate (){
        if(validateRequired()){
            if ( (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) && !checkUsersHasMoney() ) {
                    return false;
            }     
        }
        else {
            return false;
        }
        return true;
    }
    
    private boolean checkUsersHasMoney() {
        if(fromAccount.getBalance()<= (transaction.getAmount()*transaction.getFromrate())){
            errors.add("Not enough balance");
            return false;
        }
        return true;
    }
    
    /** Check whether the required fields our there.
     * @return  */
    private boolean validateRequired() {
        if (transaction.getToaccountid() == 0) {
            errors.add("Receiver account number is not valid.");
            return false;
        }
        if  (transaction.getTranstype() == null) {
            errors.add("Transaction type is not set.");
            return false;
        }
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {
            if (transaction.getFromaccountid()== 0) {
                errors.add("Sender account number is not valid..");
                return false;
            }
            if (transaction.getFromrate()==0.0f){
                errors.add("From currency exchange rate missing..");
                return false;
            }
        }
        if (transaction.getAmount()==0.0f) {            
             errors.add("Transfer amount is not valid..");
             return false;
        }
        if (toAccount == null) {
            errors.add("Receiver account account found");
            return false;
        }
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {
            if (fromAccount == null) {
                errors.add("Sender account found");
                return false;
             }
            if(transaction.getFromcurrency().isEmpty()){
                transaction.setFromcurrency(fromAccount.getCurrency());
            }
        }
        if(transaction.getTocurrency().isEmpty()){
                transaction.setTocurrency(toAccount.getCurrency());
        }      
        if (transaction.getTorate()==0.0f){
            errors.add("To currency exchange rate missing..");
            return false;
        }
        return true;
    }
    
    private void saveTransaction() {
        transactionRepository.save(transaction);
    }
    
    private void updateAccounts() {
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {
            fromAccount.setBalance(fromAccount.getBalance()-(transaction.getAmount()*transaction.getFromrate()));
            accountRepository.save(fromAccount);
        }
        toAccount.setBalance(toAccount.getBalance()+(transaction.getAmount()*transaction.getTorate()));
        accountRepository.save(toAccount);
    }
}   
