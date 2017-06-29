package com.app.annotation;

import com.app.enties.Account;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/** Created by Pasindu on 6/29/17. */
@Documented
@Constraint(validatedBy = checkdbValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checkdb {
  String message() default "Given Id invalid";

  boolean userCheck() default false;

  Class<?> entityClass() default Account.class;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
