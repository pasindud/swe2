package com.app.annotation;

import com.app.enties.Account;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/** Annotation to validate provided ids. */
@Documented
@Constraint(validatedBy = CheckdbValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checkdb {
  /** Default error message. */
  String message() default "Given Id invalid";
  /** Whether the users relationship with the id should be validated. */
  boolean userCheck() default false;
  /** Class type of the id to be validated. */
  Class<?> entityClass() default Account.class;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
