/**
 * This file was generated by the Jeddict
 */
package com.app.enties;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MerchantServices")
@IdClass(MerchantServicesPK.class)
public class MerchantServices {

    @Column(name = "serviceId", table = "MerchantServices", nullable = false)
    @Id
    private int serviceId;

    @Column(name = "Merchant_userId", table = "MerchantServices", nullable = false)
    @Id
    private int merchantuserId;

    @Column(name = "descrition", table = "MerchantServices", length = 45)
    @Basic
    private String descrition;

    @Column(name = "serviceLogpURL", table = "MerchantServices", length = 45)
    @Basic
    private String serviceLogpURL;

    @ManyToOne(optional = false, targetEntity = Merchant.class)
    @JoinColumn(name = "MERCHANT_USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    private Merchant merchant;

    @OneToMany(targetEntity = Account.class, mappedBy = "merchantServices")
    private List<Account> accountCollection;

    public int getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getMerchantuserId() {
        return this.merchantuserId;
    }

    public void setMerchantuserId(int merchantuserId) {
        this.merchantuserId = merchantuserId;
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

    public Merchant getMerchant() {
        return this.merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<Account> getAccountCollection() {
        return this.accountCollection;
    }

    public void setAccountCollection(List<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

}
