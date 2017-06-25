package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

  @Column(name = "customerid", nullable = false)
  @Id
  private Integer customerid;

  @Column(name = "title", length = 5)
  @Basic
  private String title;

  @Column(name = "firstName", length = 45)
  @Basic
  private String firstName;

  @Column(name = "lostName", length = 45)
  @Basic
  private String lostName;

  @Column(name = "dob", length = 45)
  @Basic
  private String dob;

  @OneToOne(optional = false, targetEntity = Users.class)
  private Users users;

  public Integer getUserId() {
    return this.customerid;
  }

  public void setUserId(Integer userId) {
    this.customerid = userId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLostName() {
    return this.lostName;
  }

  public void setLostName(String lostName) {
    this.lostName = lostName;
  }

  public String getDob() {
    return this.dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public Users getUsers() {
    return this.users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }
}
