package com.app.enties;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Account")
public class Account {

  @Column(name = "accountid", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer accountid;

  @Column(name = "expireDate")
  @Basic
  @Temporal(TemporalType.DATE)
  private Date expireDate;

  @Column(name = "createdDate")
  @Basic
  @Temporal(TemporalType.DATE)
  private Date createdDate;

  @Column(name = "balance", precision = 12)
  @Basic
  private Float balance;

  @Column(name = "currency", length = 45)
  @Basic
  private String currency;

  @ManyToOne(optional = false, targetEntity = AccountType.class)
  @JoinColumn(name = "ACCTYPEID", referencedColumnName = "ACCTYPEID")
  private AccountType accTypeId;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userid;

  @ManyToOne(optional = false, targetEntity = Branch.class)
  @JoinColumn(name = "BRANCHID", referencedColumnName = "BRANCHID")
  private Branch branchId;

  public Integer getAccountid() {
    return this.accountid;
  }

  public void setAccountid(Integer accountid) {
    this.accountid = accountid;
  }

  public Date getExpireDate() {
    return this.expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public Date getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Float getBalance() {
    return this.balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public AccountType getAccTypeId() {
    return this.accTypeId;
  }

  public void setAccTypeId(AccountType accTypeId) {
    this.accTypeId = accTypeId;
  }

  public Users getUserId() {
    return this.userid;
  }

  public void setUserId(Users userId) {
    this.userid = userId;
  }

  public Branch getBranchId() {
    return this.branchId;
  }

  public void setBranchId(Branch branchId) {
    this.branchId = branchId;
  }
}
