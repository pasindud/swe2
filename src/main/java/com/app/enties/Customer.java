package com.app.enties;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer customerid;

  @Column(name = "title", length = 5)
  @Basic
  private String title;

  @Column(name = "firstName", length = 45)
  @Basic
  private String firstName;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "lastName", length = 45)
  @Basic
  private String lastName;

  @Column(name = "dob", length = 45)
  @Basic
  private String dob;

  @Column(name = "nic", length = 45)
  @Basic
  private String nic;

  @Column(name = "addressLine1", length = 45)
  @Basic
  private String addressLine1;


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Column(name = "gender", length = 45)
  @Basic
  private String gender;


  @Column(name = "addressLine2", length = 45)
  @Basic
  private String addressLine2;

  @Column(name = "addressLine3", length = 45)
  @Basic
  private String addressLine3;

  @Column(name = "city", length = 45)
  @Basic
  private String city;

  @Column(name = "telephoneNo", length = 45)
  @Basic
  private String telephoneNo;

  @Column(name = "mobileNo", length = 45)
  @Basic
  private String mobileNo;

  @Column(name = "email", length = 45)
  @Basic
  private String email;

  @Column(name = "faxNo", length = 45)
  @Basic
  private String faxNo;

  @JsonIgnore
  @OneToOne(mappedBy = "customer")
  private Users users;

  public Integer getUserId() {
    return this.customerid;
  }

  public void setUserId(Integer userId) {
    this.customerid = userId;
  }

  public String getNic() {
    return this.nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
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

  public String getDob() {
    return this.dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getAddressLine1() {
    return this.addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return this.addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getAddressLine3() {
    return this.addressLine3;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getTelephoneNo() {
    return this.telephoneNo;
  }

  public void setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
  }

  public String getMobileNo() {
    return this.mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFaxNo() {
    return this.faxNo;
  }

  public void setFaxNo(String faxNo) {
    this.faxNo = faxNo;
  }

  public Users getUsers() {
    return this.users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }
}
