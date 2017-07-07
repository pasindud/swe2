

package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.app.enties.Transaction;
import com.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/** @author Pasindu */
public class Utils {
  Utils() {}
  @Autowired
  TransactionRepository transactionRepository;
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

  public static class FreqAmount {
    public long freq;
    public double amounts;

  }


  public static int randInt(int min, int max) {
    Random rand=new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
}
}
