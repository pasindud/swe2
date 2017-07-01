package com.app.enties;

import org.hibernate.annotations.CreationTimestamp;

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

  public Date getCreatedtime() {
    return createdtime;
  }

  public void setCreatedtime(Date createdtime) {
    this.createdtime = createdtime;
  }

  @Column(name = "createdtime")
  @Basic
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdtime = new Date();

  @Column(name = "ipaddress", length = 132)
  @Basic
  private String ipaddress;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "username", length = 132)
  @Basic
  private String username;


  public int getAuthorized() {
    return authorized;
  }

  public void setAuthorized(int authorized) {
    this.authorized = authorized;
  }

  private int authorized;

//  @ManyToOne(optional = false, targetEntity = Users.class)
//  @JoinColumn(name = "USERID", referencedColumnName = "USERID", nullable = true)
//  private Users userid;

//  private int userid;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getIpaddress() {
    return this.ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

//
//  public Users getUserid() {
//    return this.userid;
//  }
//
//  public void setUserid(Users userid) {
//    this.userid = userid;
//  }
}
