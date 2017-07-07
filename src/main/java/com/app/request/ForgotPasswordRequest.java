package com.app.request;

import com.app.enties.SecurityAnswers;

import java.util.List;

/**
 * Created by Pasindu on 7/7/17.
 */
public class ForgotPasswordRequest {
  String username;
  String password;

  List<SecurityAnswers> answers;

  public List <SecurityAnswers> getAnswers() {
    return answers;
  }

  public void setAnswers(List <SecurityAnswers> answers) {
    this.answers = answers;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
