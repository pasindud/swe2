/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.request;

/** @author dilsh maps the values of create user http request */
public class CreateUserRequest {
  String username;
  String password;
  String passwordConfirm;

  public CreateUserRequest() {}

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }
}
