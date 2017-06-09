package com.app;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
  private Long id;
  private String name;
  private Set<User> users;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToMany(mappedBy = "roles")
  public Set<User> getUsers() {
    if (users == null) {
      return new HashSet<User>();
    }
    return users;
  }

  public void setUsers(Set<User> users) {
    if (users == null || users.isEmpty()) {
      this.users = new HashSet<User>();
    }
    this.users = users;
  }
}
