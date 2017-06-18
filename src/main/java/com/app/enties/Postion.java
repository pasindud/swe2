package com.app.enties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Postion")
public class Postion {

  @Column(name = "postionId", nullable = false)
  @Id
  private Integer postionId;

  @Column(name = "position", nullable = false, length = 45)
  @Basic(optional = false)
  private String position;

  //  @OneToMany(targetEntity = Admin.class, mappedBy = "postionId")
  //  private List<Admin> adminCollection;

  public Integer getPostionId() {
    return this.postionId;
  }

  public void setPostionId(Integer postionId) {
    this.postionId = postionId;
  }

  public String getPosition() {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
