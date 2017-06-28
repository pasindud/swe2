package com.app.repository;

import com.app.enties.AccountType;
import org.springframework.data.repository.CrudRepository;

/** Created by Pasindu on 6/29/17. */
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
  AccountType findByAccTypeId(int accountTypeId);
}
