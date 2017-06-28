

package com.app.service;

import com.app.enties.Account;
import com.app.enties.Transaction;
import com.app.repository.AccountRepository;
import com.app.repository.TransactionRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final String BANK_DEFAULT_CURRENCY="USD";

    public TransactionService() {

    }

    public List<String> do_transactions(Transaction transaction) throws Exception {
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

    private boolean validate () throws Exception{
        if(validateRequired()){
            getCurrencyRate();
            if ( (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) ) {
                if(!checkUsersHasMoney())
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
        if  (transaction.getTranstype() == null) {
            errors.add("Transaction type is not set.");
            return false;
        }
        if (transaction.getToaccountid() == 0 && !transaction.getTranstype().equalsIgnoreCase("W")) {
            errors.add("Receiver account number is not valid.");
            return false;
        }

        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {
            if (transaction.getFromaccountid()== 0) {
                errors.add("Sender account number is not valid..");
                return false;
            }

        }
        if (transaction.getAmount()==null) {
            errors.add("Transfer amount is not valid..");
            return false;
        }
        if (toAccount == null && !transaction.getTranstype().equalsIgnoreCase("W")) {
            errors.add("Receiver account account not found");
            return false;
        }
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {
            if (fromAccount == null) {
                errors.add("Sender account found");
                return false;
            }
            if(transaction.getFromcurrency()==null){
                transaction.setFromcurrency(fromAccount.getCurrency());
            }
        }
        if(transaction.getTocurrency()==null && !transaction.getTranstype().equalsIgnoreCase("W")){
            transaction.setTocurrency(toAccount.getCurrency());
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
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("D")) {
            toAccount.setBalance(toAccount.getBalance() + (transaction.getAmount() * transaction.getTorate()));
            accountRepository.save(toAccount);
        }
    }

    private void getCurrencyRate() throws Exception{

        RestTemplate restTemplate = new RestTemplate();

        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("W")) {

            Float temp1= ExchangeRates.getExchangeRate(BANK_DEFAULT_CURRENCY, transaction.getFromcurrency());
            transaction.setFromrate(new Float((Math.round( temp1 * 100.0) / 100.0)));

            transaction.setAmount(transaction.getAmount()/transaction.getFromrate());
        }
        if (transaction.getTranstype().equalsIgnoreCase("T")||transaction.getTranstype().equalsIgnoreCase("D")) {
            Float temp2=ExchangeRates.getExchangeRate(BANK_DEFAULT_CURRENCY, transaction.getTocurrency());
            transaction.setTorate(new Float((Math.round( temp2 * 100.0) / 100.0)));
            
            if(transaction.getTranstype().equalsIgnoreCase("D"))
                transaction.setAmount(transaction.getAmount()/transaction.getTorate());
        }
    }
}
