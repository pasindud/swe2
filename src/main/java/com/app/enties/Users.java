package com.app.enties;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Users")
public class Users {

  @Column(name = "userId", nullable = false)
  @Id
  private Integer userId;

  @Column(name = "userType", nullable = false, length = 45)
  @Basic(optional = false)
  private String userType;

  @Column(name = "creationDate", nullable = false)
  @Basic(optional = false)
  @Temporal(TemporalType.DATE)
  private Date creationDate;

  @OneToOne(targetEntity = Customer.class, mappedBy = "users")
  private Customer customer;

  @OneToOne(targetEntity = Merchant.class)
  private Merchant merchant;

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserType() {
    return this.userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Date getCreationDate() {
    return this.creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Merchant getMerchant() {
    return this.merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }
}
