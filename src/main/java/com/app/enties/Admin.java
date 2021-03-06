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
@Table(name = "Admin")
public class Admin {

  @Column(name = "adminid", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer adminid;

  @Column(name = "title")
  @Basic
  private Integer title;

  @Column(name = "firstName", length = 45)
  @Basic
  private String firstName;

  @Column(name = "lastName", length = 45)
  @Basic
  private String lastName;

  @Column(name = "userIdCopy", nullable = false)
  @Basic(optional = false)
  private int userIdCopy;

  @ManyToOne(optional = false, targetEntity = Users.class)
  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
  private Users userId;

  @ManyToOne(optional = false, targetEntity = Postion.class)
  @JoinColumn(name = "POSTIONID", referencedColumnName = "POSTIONID")
  private Postion postionId;

  @ManyToOne(optional = false, targetEntity = Branch.class)
  @JoinColumn(name = "BRANCHID", referencedColumnName = "BRANCHID")
  private Branch branchId;

  public Integer getAdminid() {
    return this.adminid;
  }

  public void setAdminid(Integer adminid) {
    this.adminid = adminid;
  }

  public Integer getTitle() {
    return this.title;
  }

  public void setTitle(Integer title) {
    this.title = title;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getUserIdCopy() {
    return this.userIdCopy;
  }

  public void setUserIdCopy(int userIdCopy) {
    this.userIdCopy = userIdCopy;
  }

  public Users getUserId() {
    return this.userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }

  public Postion getPostionId() {
    return this.postionId;
  }

  public void setPostionId(Postion postionId) {
    this.postionId = postionId;
  }

  public Branch getBranchId() {
    return this.branchId;
  }

  public void setBranchId(Branch branchId) {
    this.branchId = branchId;
  }
}
