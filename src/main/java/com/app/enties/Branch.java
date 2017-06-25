package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Branch")
public class Branch {

  @Column(name = "branchId", nullable = false)
  @Id
  private Integer branchId;

  @Column(name = "branchName", length = 45)
  @Basic
  private String branchName;

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

  @Column(name = "email", length = 45)
  @Basic
  private String email;

  @Column(name = "faxNo", length = 45)
  @Basic
  private String faxNo;

  public Integer getBranchId() {
    return this.branchId;
  }

  public void setBranchId(Integer branchId) {
    this.branchId = branchId;
  }

  public String getBranchName() {
    return this.branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
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
}
