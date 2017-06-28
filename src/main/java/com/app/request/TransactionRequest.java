package com.app.request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/** Created by Pasindu on 6/29/17. */
public class TransactionRequest {
  @NotNull private Integer toaccount;
  @NotNull private Integer fromaccountid;
  @NotNull private String transtype;

  @Min(value = 1)
  private float amount = -1;

  @NotNull private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getToaccount() {
    return toaccount;
  }

  public void setToaccount(int toaccount) {
    this.toaccount = toaccount;
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
}
