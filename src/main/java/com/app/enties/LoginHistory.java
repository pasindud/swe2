package com.app.enties;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LoginHistory")
public class LoginHistory {

  @Column(name = "id", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "time")
  @Basic
  @Temporal(TemporalType.DATE)
  private Date time;

  @Column(name = "ipAddress", length = 132)
  @Basic
  private String ipAddress;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getTime() {
    return this.time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getIpAddress() {
    return this.ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }
}