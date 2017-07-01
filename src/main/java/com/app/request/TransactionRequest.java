package com.app.request;

import com.app.annotation.Checkdb;
import com.app.enties.Account;
import com.app.enties.Users;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/** Created by Pasindu on 6/29/17. */
public class TransactionRequest {
  @NotNull private Integer toaccountid;
  @NotNull private Integer fromaccountid;

  @Enumerated(EnumType.STRING)
  private String transtype;

  @Min(value = 1)
  private float amount = -1;

  @NotNull private String message;
  @NotNull private Users userId;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getToaccountid() {
    return toaccountid;
  }

  public void setToaccountid(int toaccount) {
    this.toaccountid = toaccount;
  }

  public int getFromaccountid() {
    return fromaccountid;
  }

  public void setFromaccountid(int fromaccountid) {
    this.fromaccountid = fromaccountid;
  }

  public String getTranstype() {
    return transtype;
  }

  public void setTranstype(String transtype) {
    this.transtype = transtype;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public Users getUserId() {
    return userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }

}
