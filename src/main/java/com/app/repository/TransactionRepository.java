

package com.app.repository;

import com.app.enties.Transaction;
import org.springframework.data.repository.CrudRepository;

/** @author Pasindu */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {}
