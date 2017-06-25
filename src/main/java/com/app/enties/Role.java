package com.app.enties;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
  private Long id;
  private String name;
  private Set<Users> users;

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
  public Set<Users> getUsers() {
    if (users == null) {
      return new HashSet<Users>();
    }
    return users;
  }

  public void setUsers(Set<Users> users) {
    if (users == null || users.isEmpty()) {
      this.users = new HashSet<Users>();
    }
    this.users = users;
  }
}
