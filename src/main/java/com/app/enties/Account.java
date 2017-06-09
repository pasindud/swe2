/**
 * This file was generated by the Jeddict
 */
package com.app.enties;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Account")
@IdClass(AccountPK.class)
public class Account {

    @Column(name = "accountId", table = "Account", nullable = false)
    @Id
    private int accountId;

    @Column(name = "MerchantServices_serviceId", table = "Account", nullable = false)
    @Id
    private int merchantServicesserviceId;

    @Column(name = "MerchantServices_Merchant_userId", table = "Account", nullable = false)
    @Id
    private int merchantServicesMerchantuserId;

    @Column(name = "expireDate", table = "Account")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @Column(name = "createdDate", table = "Account")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "balance", table = "Account", precision = 12)
    @Basic
    private Float balance;

    @Column(name = "currency", table = "Account", length = 45)
    @Basic
    private String currency;

    @ManyToOne(optional = false, targetEntity = AccountType.class)
    @JoinColumn(name = "ACCTYPEID", referencedColumnName = "ACCTYPEID")
    private AccountType accTypeId;

    @ManyToOne(optional = false, targetEntity = Branch.class)
    @JoinColumn(name = "BRANCHID", referencedColumnName = "BRANCHID")
    private Branch branchId;

    @ManyToOne(optional = false, targetEntity = MerchantServices.class)
    @JoinColumns({
        @JoinColumn(name = "MERCHANTSERVICES_SERVICEID", referencedColumnName = "SERVICEID", insertable = false, updatable = false)
        ,@JoinColumn(name = "MERCHANTSERVICES_MERCHANT_USERID", referencedColumnName = "MERCHANT_USERID", insertable = false, updatable = false)})
    private MerchantServices merchantServices;

    @ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    private User userId;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "accountId")
    private List<Transaction> transactionCollection;

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getMerchantServicesserviceId() {
        return this.merchantServicesserviceId;
    }

    public void setMerchantServicesserviceId(int merchantServicesserviceId) {
        this.merchantServicesserviceId = merchantServicesserviceId;
    }

    public int getMerchantServicesMerchantuserId() {
        return this.merchantServicesMerchantuserId;
    }

    public void setMerchantServicesMerchantuserId(int merchantServicesMerchantuserId) {
        this.merchantServicesMerchantuserId = merchantServicesMerchantuserId;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getAccTypeId() {
        return this.accTypeId;
    }

    public void setAccTypeId(AccountType accTypeId) {
        this.accTypeId = accTypeId;
    }

    public Branch getBranchId() {
        return this.branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public MerchantServices getMerchantServices() {
        return this.merchantServices;
    }

    public void setMerchantServices(MerchantServices merchantServices) {
        this.merchantServices = merchantServices;
    }

    public User getUserId() {
        return this.userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactionCollection() {
        return this.transactionCollection;
    }

    public void setTransactionCollection(List<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

}
