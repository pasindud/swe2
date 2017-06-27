package com.app.tests;

import com.app.enties.Transaction;
import com.app.service.TransactionService;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 *
 * @author Pasindu
 */
public class TransactionServiceTests {
    
    TransactionService transactionService;
    
    @Test
    public void testRequired() {
        Transaction transaction = new Transaction();
        
        transactionService = new TransactionService(transaction);
        List<String> errors = transactionService.do_transactions();
        assertNotNull(errors);
    }
}
