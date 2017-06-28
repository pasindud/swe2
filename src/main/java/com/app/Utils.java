

package com.app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/** @author Pasindu */
public class Utils {
  Utils() {}

  public static String getCurrentUsers() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getUsername();
  }

  public static List<String> getListFromErrors(Errors requestError) {
    List<String> errors = new ArrayList<>();
    for (ObjectError objectError : requestError.getAllErrors()) {
      errors.add(objectError.getDefaultMessage());
    }
    return errors;
  }
}
