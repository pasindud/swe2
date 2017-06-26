package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

  @Column(name = "customerid", nullable = false)
  @Id
  private Integer customerid;

  @Column(name = "title", length = 5)
  @Basic
  private String title;

  @Column(name = "firstName", length = 45)
  @Basic
  private String firstName;

  @Column(name = "lostName", length = 45)
  @Basic
  private String lostName;

  @Column(name = "dob", length = 45)
  @Basic
  private String dob;

  @Column(name = "addressLine1", length = 45)
  @Basic
  private String addressLine1;

  @Column(name = "addressLine2", length = 45)
  @Basic
  private String addressLine2;

  @Column(name = "addressLine3", length = 45)
  @Basic
  private String addressLine3;

  @Column(name = "city", length = 45)
  @Basic
  private String city;

  @Column(name = "telephoneNo", length = 45)
  @Basic
  private String telephoneNo;

  @Column(name = "mobileNo", length = 45)
  @Basic
  private String mobileNo;

  @Column(name = "email", length = 45)
  @Basic
  private String email;

  @Column(name = "faxNo", length = 45)
  @Basic
  private String faxNo;

  @OneToOne(optional = false, targetEntity = Users.class)
  private Users users;

  public Integer getUserId() {
    return this.customerid;
  }

  public void setUserId(Integer userId) {
    this.customerid = userId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLostName() {
    return this.lostName;
  }

  public void setLostName(String lostName) {
    this.lostName = lostName;
  }

  public String getDob() {
    return this.dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getAddressLine1() {
    return this.addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return this.addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getAddressLine3() {
    return this.addressLine3;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getTelephoneNo() {
    return this.telephoneNo;
  }

  public void setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
  }

  public String getMobileNo() {
    return this.mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFaxNo() {
    return this.faxNo;
  }

  public void setFaxNo(String faxNo) {
    this.faxNo = faxNo;
  }

  public Users getUsers() {
    return this.users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }
}
