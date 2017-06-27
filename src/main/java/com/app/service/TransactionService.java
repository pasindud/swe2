

package com.app.service;

import com.app.enties.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pasindu
 */
public class TransactionService {
    private Transaction transaction;
    private List<String> errors = new ArrayList<String>();
    
    public TransactionService(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public List<String> do_transactions() {
        validateRequired();
        return errors;
    }
    
    private void validate (){
    }
    
    private void checkUsersHasMoney() {
        
    }
    
    /** Check whether the required fields our there.
     * @return  */
    private void validateRequired() {
        if (transaction.getToaccountid() == 0) {
            errors.add("Transaction id not avilable.");
        }
    }
    
    private void saveTransaction() {
        
    }
}   
