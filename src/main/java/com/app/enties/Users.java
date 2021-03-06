package com.app.enties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Users")
public class Users {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Integer userId;

  private String username;

//  @JsonIgnore()
  private String password;

  public Set <Role> getRoles() {
    return roles;
  }

  public void setRoles(Set <Role> roles) {
    this.roles = roles;
  }

  /**
   * Removed due FK error.
   */
  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "user_role",
          // joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  @Column(name = "userType", length = 45)
  @Enumerated(EnumType.STRING)
  private UserType userType;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creationDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:MM")
  private Date creationDate;

  // Comment to avoid a loop.
  //  @OneToOne

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(targetEntity = Merchant.class)
  private Merchant merchant;

  public List <SecurityAnswers> getSecurityAnswers() {
    return securityAnswers;
  }


  public void setSecurityAnswers(List <SecurityAnswers> securityAnswers) {
    this.securityAnswers = securityAnswers;
  }

  @JsonIgnore
  @OneToMany(targetEntity = SecurityAnswers.class)
  @JoinColumn(referencedColumnName = "userId")
  private List<SecurityAnswers> securityAnswers;

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  @Column(name="locked", columnDefinition = "boolean default true", nullable = false)
  private Boolean locked = true;

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

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }


  public UserType getUserType() {
    return this.userType;
  }

  public void setUserType(UserType userType) {
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
