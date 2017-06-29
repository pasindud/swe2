package com.app.annotation;

import com.app.enties.Account;
import com.app.enties.MerchantServices;
import com.app.repository.AccountRepository;
import com.app.repository.MerchantServicesRepository;
import com.app.service.UserServiceImpl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class checkdbValidator implements ConstraintValidator<Checkdb, Integer> {
  @Autowired AccountRepository accountRepository;
  @Autowired MerchantServicesRepository merchantServicesRepository;
  @Autowired UserServiceImpl userService;
  Class<?> entityClass;
  boolean userCheck;

  @Override
  public void initialize(Checkdb param) {
    this.entityClass = param.entityClass();
    this.userCheck = param.userCheck();
  }

  public boolean isValid(Integer id, ConstraintValidatorContext ctx) {
    if (entityClass == Account.class) {
      Account account = accountRepository.findByAccountid(id);
      if (account == null) {
        return false;
      } else if (userCheck == false) {
        return true;
      } else if (account.getUserId().getUserId() == userService.getLoggedInUserId()) {
        return true;
      } else {
        return false;
      }
    } else if (MerchantServices.class == entityClass
        && merchantServicesRepository.findByServiceid(id) != null) {
      return true;
    }
    return false;
  }
}
