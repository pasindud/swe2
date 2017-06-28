package com.app.repository;

import com.app.enties.Branch;
import org.springframework.data.repository.CrudRepository;

/** Created by Pasindu on 6/29/17. */
public interface BranchRepository extends CrudRepository<Branch, Long> {
  Branch findByBranchId(int branchId);
}
