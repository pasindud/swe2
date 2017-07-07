package com.app.enties;

import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * Created by Pasindu on 7/8/17.
 */

@Entity
@javax.persistence.Table(name = "SuspiciousLog")
public class SuspiciousLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String message;

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
