package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccountType")
public class AccountType {

  @Column(name = "accTypeId", nullable = false)
  @Id
  private Integer accTypeId;

  @Column(name = "accName", length = 45)
  @Basic
  private String accName;

  @Column(name = "accInterestRates", precision = 12)
  @Basic
  private Float accInterestRates;

  @Column(name = "minInitBalance", precision = 12)
  @Basic
  private Float minInitBalance;

  @Column(name = "minAvgBalance", precision = 12)
  @Basic
  private Float minAvgBalance;

  @Column(name = "minMonths")
  @Basic
  private Integer minMonths;

  @Column(name = "maxMonths")
  @Basic
  private Integer maxMonths;

  @Column(name = "maxOverDraftAmount", precision = 12)
  @Basic
  private Float maxOverDraftAmount;

  @Column(name = "dailyWithdrawLimit", precision = 12)
  @Basic
  private Float dailyWithdrawLimit;

  public Integer getAccTypeId() {
    return this.accTypeId;
  }

  public void setAccTypeId(Integer accTypeId) {
    this.accTypeId = accTypeId;
  }

  public String getAccName() {
    return this.accName;
  }

  public void setAccName(String accName) {
    this.accName = accName;
  }

  public Float getAccInterestRates() {
    return this.accInterestRates;
  }

  public void setAccInterestRates(Float accInterestRates) {
    this.accInterestRates = accInterestRates;
  }

  public Float getMinInitBalance() {
    return this.minInitBalance;
  }

  public void setMinInitBalance(Float minInitBalance) {
    this.minInitBalance = minInitBalance;
  }

  public Float getMinAvgBalance() {
    return this.minAvgBalance;
  }

  public void setMinAvgBalance(Float minAvgBalance) {
    this.minAvgBalance = minAvgBalance;
  }

  public Integer getMinMonths() {
    return this.minMonths;
  }

  public void setMinMonths(Integer minMonths) {
    this.minMonths = minMonths;
  }

  public Integer getMaxMonths() {
    return this.maxMonths;
  }

  public void setMaxMonths(Integer maxMonths) {
    this.maxMonths = maxMonths;
  }

  public Float getMaxOverDraftAmount() {
    return this.maxOverDraftAmount;
  }

  public void setMaxOverDraftAmount(Float maxOverDraftAmount) {
    this.maxOverDraftAmount = maxOverDraftAmount;
  }

  public Float getDailyWithdrawLimit() {
    return dailyWithdrawLimit;
  }

  public void setDailyWithdrawLimit(Float dailyWithdrawLimit) {
    this.dailyWithdrawLimit = dailyWithdrawLimit;
  }
}
