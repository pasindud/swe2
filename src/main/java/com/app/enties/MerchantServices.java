package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MerchantServices")
public class MerchantServices {
    @Column(name = "serviceid", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer serviceid;

    @Column(name = "servicename", length = 45)
    @Basic
    private String servicename;
    
    @Column(name = "description", length = 45)
    @Basic
    private String description;

    @Column(name = "accountid", length = 45)
    private Integer accountid;

   @ManyToOne(optional = false, targetEntity = Merchant.class)
   @JoinColumn(name = "MERCHANTID", referencedColumnName = "MERCHANTID")
   private Merchant fkmerchantid;

    public Integer getServiceid() {
        return serviceid;
    }
    
    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
    
    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

   public Merchant getFkmerchantid() {
       return fkmerchantid;
   }

   public void setFkmerchantid(Merchant fkmerchantid) {
       this.fkmerchantid = fkmerchantid;
   }

}
