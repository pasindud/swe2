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
  @Temporal(TemporalType.TIMESTAMP)
  private Date time = new Date();

  @Column(name = "ipaddress", length = 132)
  @Basic
  private String ipaddress;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userid;

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

  public String getIpaddress() {
    return this.ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

  public Users getUserid() {
    return this.userid;
  }

  public void setUserid(Users userid) {
    this.userid = userid;
  }
}
