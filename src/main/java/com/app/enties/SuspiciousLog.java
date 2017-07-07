package com.app.enties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pasindu on 7/8/17.
 */

@Entity
@Table(name = "SuspiciousLog")
public class SuspiciousLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String message;

  @Column(name = "createdDate")
  @Basic
  @Temporal(TemporalType.DATE)
  private Date createdDate;

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
