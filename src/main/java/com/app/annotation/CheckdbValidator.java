package com.app.annotation;

import com.app.enties.Account;
import com.app.enties.MerchantServices;
import com.app.repository.AccountRepository;
import com.app.repository.MerchantServicesRepository;
import com.app.service.AccountService;
import com.app.service.UserServiceImpl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class for validator ids.
 */
public class CheckdbValidator implements ConstraintValidator<Checkdb, Integer> {
  private static final Logger logger = LoggerFactory.getLogger(CheckdbValidator.class);
  /** Repository for accessing account data. */
  @Autowired AccountRepository accountRepository;
  /** Repository for acessing merchant services data. */
  @Autowired MerchantServicesRepository merchantServicesRepository;
  /** Class used for user operations. */
  @Autowired UserServiceImpl userService;
  /** */
  @Autowired
  AccountService accountService;
  /** Class type of the id to be validated. */
  Class<?> entityClass;
  /** Whether the users relationship with the id should be validated. */
  boolean userCheck;

  @Override
  public void initialize(Checkdb param) {
    this.entityClass = param.entityClass();
    this.userCheck = param.userCheck();
  }

  /**
   * Check whether the given id exists in the @{entityClass}.
   * @param id the id to be validate.
   * @param ctx the constrain context.
   * @return whether the id is exists or not.
   */
  public boolean isValid(Integer id, ConstraintValidatorContext ctx) {
    if (id == null) {
      return false;
    }
    if (entityClass == Account.class) {
      return accountService.checkUserHasPermissions(id, userCheck);
    } else if (MerchantServices.class == entityClass
        && merchantServicesRepository.findByServiceid(id) != null) {
      return true;
    }
    return false;
  }
}
