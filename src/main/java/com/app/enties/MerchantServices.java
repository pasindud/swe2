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
@Table(name = "MerchantServices")
public class MerchantServices {

  @Column(name = "serviceId", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer serviceId;

  @Column(name = "descrition", length = 45)
  @Basic
  private String descrition;

  @Column(name = "serviceLogpURL", length = 45)
  @Basic
  private String serviceLogpURL;

  @ManyToOne(optional = false, targetEntity = Merchant.class)
  @JoinColumn(name = "MERCHANT_USERID", referencedColumnName = "merchantId")
  private Merchant merchantuserId;

  public Integer getServiceId() {
    return this.serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public String getDescrition() {
    return this.descrition;
  }

  public void setDescrition(String descrition) {
    this.descrition = descrition;
  }

  public String getServiceLogpURL() {
    return this.serviceLogpURL;
  }

  public void setServiceLogpURL(String serviceLogpURL) {
    this.serviceLogpURL = serviceLogpURL;
  }

  public Merchant getMerchantuserId() {
    return this.merchantuserId;
  }

  public void setMerchantuserId(Merchant merchantuserId) {
    this.merchantuserId = merchantuserId;
  }
}
