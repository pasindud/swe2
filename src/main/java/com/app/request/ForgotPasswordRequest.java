package com.app.request;

import com.app.enties.SecurityAnswers;

import java.util.List;

/**
 * Created by Pasindu on 7/7/17.
 */
public class ForgotPasswordRequest {
  String username;
  String password;

  public List <SecurityAnswers> getAnswerslist() {
    return answerslist;
  }

  public void setAnswerslist(List <SecurityAnswers> answerslist) {
    this.answerslist = answerslist;
  }

  List<SecurityAnswers> answerslist;

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
