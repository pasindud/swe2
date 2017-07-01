package com.app.enties;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Integer userId;

  private String username;
  private String password;

  @ManyToMany
  @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;

  @Column(name = "userType", length = 45)
  private String userType;

  @Column(name = "creationDate")
  @Temporal(TemporalType.DATE)
  private Date creationDate;

  // Comment to avoid a loop.
  //  @OneToOne

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(targetEntity = Merchant.class)
  private Merchant merchant;

  private boolean activate = true;
  private boolean timeLocked = true;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getUserType() {
    return this.userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Date getCreationDate() {
    return this.creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Merchant getMerchant() {
    return this.merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }
}
