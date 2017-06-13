package com.app.enties;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
  private Long id;
  private String name;
  private Set<SpringUserStatic> users;

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
  public Set<SpringUserStatic> getUsers() {
    if (users == null) {
      return new HashSet<SpringUserStatic>();
    }
    return users;
  }

  public void setUsers(Set<SpringUserStatic> users) {
    if (users == null || users.isEmpty()) {
      this.users = new HashSet<SpringUserStatic>();
    }
    this.users = users;
  }
}
