package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

  @Column(name = "transactionid", nullable = false)
  @Id
  private Integer transactionid;

  @Column(name = "toaccountid", nullable = false)
  @Basic(optional = false)
  private int toaccountId;

  @Column(name = "fromaccountid", nullable = false)
  @Basic(optional = false)
  private int fromaccountid;

  @Column(name = "transtype", length = 45)
  @Basic
  private String transtype;

  @Column(name = "currency", length = 45)
  @Basic
  private String currency;

  @Column(name = "amount", precision = 12)
  @Basic
  private Float amount;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getTransactionid() {
    return this.transactionid;
  }

  public void setTransactionid(Integer transactionId) {
    this.transactionid = transactionId;
  }

  public int getToaccountid() {
    return this.toaccountId;
  }

  public void setToaccountid(int accountId) {
    this.toaccountId = accountId;
  }

  public int getFromaccountid() {
    return this.fromaccountid;
  }

  public void setFromaccountid(int accountId) {
    this.fromaccountid = accountId;
  }

  public String getTranstype() {
    return this.transtype;
  }

  public void setTranstype(String transType) {
    this.transtype = transType;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Float getAmount() {
    return this.amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }
}
