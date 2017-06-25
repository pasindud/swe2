package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ContactInfo")
public class ContactInfo {

  @Column(name = "contactId", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer contactId;

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

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getContactId() {
    return this.contactId;
  }

  public void setContactId(Integer contactId) {
    this.contactId = contactId;
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

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }
}
