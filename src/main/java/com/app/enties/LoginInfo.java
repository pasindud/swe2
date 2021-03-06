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
@Table(name = "LoginInfo")
public class LoginInfo {

  @Column(name = "loginfoid", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer loginfoid;

  @Column(name = "userName", nullable = false, length = 45)
  @Basic(optional = false)
  private String userName;

  @Column(name = "password", nullable = false, length = 45)
  @Basic(optional = false)
  private String password;

  @Column(name = "sequrityAns", length = 45)
  @Basic
  private String sequrityAns;

  @Column(name = "defaultIP", length = 132)
  @Basic
  private String defaultIP;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  public Integer getLoginfoid() {
    return this.loginfoid;
  }

  public void setLoginfoid(Integer loginfoid) {
    this.loginfoid = loginfoid;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSequrityAns() {
    return this.sequrityAns;
  }

  public void setSequrityAns(String sequrityAns) {
    this.sequrityAns = sequrityAns;
  }

  public String getDefaultIP() {
    return this.defaultIP;
  }

  public void setDefaultIP(String defaultIP) {
    this.defaultIP = defaultIP;
  }

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }
}
