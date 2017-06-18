package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Merchant")
public class Merchant {

  @Column(name = "userId", nullable = false)
  @Id
  private Integer userId;

  @Column(name = "OrgName", nullable = false, length = 45)
  @Basic(optional = false)
  private String orgName;

  @Column(name = "registrationNo", nullable = false, length = 45)
  @Basic(optional = false)
  private String registrationNo;

  @Column(name = "taxNo", nullable = false, length = 45)
  @Basic(optional = false)
  private String taxNo;

  @Column(name = "logoUrl")
  @Basic
  private String logoUrl;

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getOrgName() {
    return this.orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getRegistrationNo() {
    return this.registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getTaxNo() {
    return this.taxNo;
  }

  public void setTaxNo(String taxNo) {
    this.taxNo = taxNo;
  }

  public String getLogoUrl() {
    return this.logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }
}
