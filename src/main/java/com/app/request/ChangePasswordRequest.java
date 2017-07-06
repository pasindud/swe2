package com.app.request;

/**
 * Created by Pasindu on 7/6/17.
 */
public class ChangePasswordRequest {
  private String current;
  private String newPassword;

  public String getCurrent() {
    return current;
  }

  public void setCurrent(String current) {
    this.current = current;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
