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

  @Column(name = "transactionId", nullable = false)
  @Id
  private Integer transactionId;

  @Column(name = "accountId", nullable = false)
  @Basic(optional = false)
  private int accountId;

  @Column(name = "transType", length = 45)
  @Basic
  private String transType;

  @Column(name = "currency", length = 45)
  @Basic
  private String currency;

  @Column(name = "amount", precision = 12)
  @Basic
  private Float amount;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getTransType() {
    return this.transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
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