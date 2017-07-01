package com.app.enties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Transaction")
public class Transaction {

  @Column(name = "transactionid", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer transactionid;

  @Column(name = "toaccountid", nullable = false)
  @Basic(optional = false)
  private int toaccountId;

  @Column(name = "fromaccountid", nullable = false)
  @Basic(optional = false)
  private int fromaccountid;

  @Column(name = "transtype", length = 1)
  @Basic
  @Enumerated(EnumType.STRING)
  private TransactionType transtype;

  @Column(name = "fromcurrency", length = 45)
  @Basic
  private String fromcurrency;

  @Column(name = "tocurrency", length = 45)
  @Basic
  private String tocurrency;

  @Column(name = "fromrate", precision = 12)
  @Basic
  private Float fromrate;

  @Column(name = "torate", precision = 12)
  @Basic
  private Float torate;

  @Column(name = "amount", precision = 12)
  @Basic
  private Float amount;

  @Column(name = "message", length = 50)
  @Basic
  private String message;


  public Date getTransactiontime() {
    return transactiontime;
  }

  public void setTransactiontime(Date transactiontime) {
    this.transactiontime = transactiontime;
  }

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "transactiontime")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:MM")
  private Date transactiontime;

  @JsonIgnore
  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getTransactionid() {
    return this.transactionid;
  }

  public void setTransactionid(Integer transactionId) {
    this.transactionid = transactionId;
  }

  public int getToaccountid() {
    return this.toaccountId;
  }

  public void setToaccountid(int accountId) {
    this.toaccountId = accountId;
  }

  public int getFromaccountid() {
    return this.fromaccountid;
  }

  public void setFromaccountid(int accountId) {
    this.fromaccountid = accountId;
  }

  public TransactionType getTranstype() {
    return this.transtype;
  }

  public void setTranstype(TransactionType transType) {
    this.transtype = transType;
  }

  public Float getAmount() {
    return this.amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getFromcurrency() {
    return fromcurrency;
  }

  public String getTocurrency() {
    return tocurrency;
  }

  public Float getFromrate() {
    return fromrate;
  }

  public Float getTorate() {
    return torate;
  }

  public void setFromcurrency(String fromcurrency) {
    this.fromcurrency = fromcurrency;
  }

  public void setTocurrency(String tocurrency) {
    this.tocurrency = tocurrency;
  }

  public void setFromrate(Float fromrate) {
    this.fromrate = fromrate;
  }

  public void setTorate(Float torate) {
    this.torate = torate;
  }
}
