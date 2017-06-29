package com.app.request;

/** Created by Pasindu on 6/29/17. */
public class CreateAccountRequest {
  private String currency;
  private int accTypeid;
  private int branchid;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public int getAccTypeid() {
    return accTypeid;
  }

  public void setAccTypeid(int accTypeid) {
    this.accTypeid = accTypeid;
  }

  public int getBranchid() {
    return branchid;
  }

  public void setBranchid(int branchid) {
    this.branchid = branchid;
  }
}
