package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Merchant")
public class Merchant {
  @Column(name = "merchantid", nullable = false)
  @Id
  private Integer merchantid;

  @Column(name = "orgname", nullable = false, length = 45)
  @Basic(optional = false)
  private String orgname;

  @Column(name = "registrationno", nullable = false, length = 45)
  @Basic(optional = false)
  private String registrationno;

  @Column(name = "taxno", nullable = false, length = 45)
  @Basic(optional = false)
  private String taxno;

  @Column(name = "logourl")
  @Basic
  private String logourl;

  public Integer getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(Integer merchantid) {
    this.merchantid = merchantid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getRegistrationno() {
    return registrationno;
  }

  public void setRegistrationno(String registrationno) {
    this.registrationno = registrationno;
  }

  public String getTaxno() {
    return taxno;
  }

  public void setTaxno(String taxno) {
    this.taxno = taxno;
  }

  public String getLogourl() {
    return logourl;
  }

  public void setLogourl(String logourl) {
    this.logourl = logourl;
  }
}
